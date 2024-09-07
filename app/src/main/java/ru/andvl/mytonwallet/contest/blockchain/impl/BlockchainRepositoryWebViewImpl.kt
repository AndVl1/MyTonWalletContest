package ru.andvl.mytonwallet.contest.blockchain.impl

import android.util.Log
import android.webkit.JavascriptInterface
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.ApiUpdate
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.AuthResultDto
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.TokenDto
import ru.andvl.mytonwallet.contest.blockchain.util.MNEMONIC_CHECK_COUNT
import ru.andvl.mytonwallet.contest.blockchain.util.MNEMONIC_COUNT
import ru.andvl.mytonwallet.contest.blockchain.util.WebViewHolder
import ru.andvl.mytonwallet.contest.database.daos.BalanceDao
import ru.andvl.mytonwallet.contest.database.daos.TokenDao
import ru.andvl.mytonwallet.contest.database.entities.BalanceEntity
import ru.andvl.mytonwallet.contest.mappers.toEntity
import java.math.BigInteger
import kotlin.coroutines.resume

class BlockchainRepositoryWebViewImpl(
    private val webView: WebViewHolder,
    private val balanceDao: BalanceDao,
    private val tokenDao: TokenDao
) : BlockchainRepository {
    init {
        webView.addJavascriptInterface(WebAppInterface())
    }

    private var currentAccountId: String? = null
    private var currentAccountAddress: String? = null
    private val network: String = "mainnet"

    private var continuation: CancellableContinuation<Result<String>>? = null

    override suspend fun checkApiAvailability(): Boolean {
        val jsonString = evaluateJs(
            "callApi('checkApiAvailability', {" +
                    "    blockchainKey: 'ton'," +
                    "    network: '$network'" +
                    "})"
        ).getOrThrow()

        return Json.parseToJsonElement(jsonString).jsonPrimitive.boolean
    }

    override suspend fun getMnemonicWordList(): List<String> {
        val jsonString = evaluateJs("callApi('getMnemonicWordList')").getOrThrow()

        val jsonElement = Json.parseToJsonElement(jsonString)
        return jsonElement.jsonArray.map { it.jsonPrimitive.content }
    }

    override suspend fun generateMnemonic(): List<String> {
        val jsonString = evaluateJs("callApi('generateMnemonic')").getOrThrow()

        val jsonElement = Json.parseToJsonElement(jsonString)
        return jsonElement.jsonArray.map { it.jsonPrimitive.content }
    }

    override fun getMnemonicCheckIndexes(): List<Int> {
        return List(MNEMONIC_COUNT) { it }
            .shuffled()
            .take(MNEMONIC_CHECK_COUNT)
            .sorted()
    }

    override suspend fun validateMnemonic(mnemonic: List<String>): Boolean {
        val mnemonicJson = Json.encodeToString(mnemonic)
        val jsonString = evaluateJs("callApi('validateMnemonic', $mnemonicJson)").getOrThrow()

        return Json.parseToJsonElement(jsonString).jsonPrimitive.boolean
    }

    override fun updateCurrentAccountId(id: String) {
        currentAccountId = id
    }

    override suspend fun createAccount(
        mnemonic: List<String>,
        passcode: String,
        isImport: Boolean
    ) {
        val method = if (isImport) "importMnemonic" else "createWallet"
        val mnemonicJson = Json.encodeToString(mnemonic)
        val jsonString = evaluateJs(
            """
            callApi('$method', "$network", $mnemonicJson, '$passcode')
            """.trimIndent()
        ).getOrThrow()

        val result: AuthResultDto = Json.decodeFromString(jsonString)

        if (result.error != null || result.accountId == null || result.address == null) {
            throw Exception(result.error)
        } else {
            currentAccountId = result.accountId
            currentAccountAddress = result.address
        }
    }

    override suspend fun getCurrentAccountTokenBalances(): Flow<List<BalanceEntity>> {
        currentAccountId?.let {
            return balanceDao.getBalancesForAccount(it)
        } ?: throw NoSuchElementException()
    }

    override suspend fun getCurrentAccountWalletBalance(): BigInteger {
        val jsonString = evaluateJs(
            """
            callApi('getWalletBalance', "$network", "$currentAccountAddress")
            """.trimIndent()
        ).getOrThrow()

        return BigInteger(Json.decodeFromString<String>(jsonString))
    }

    private suspend fun evaluateJs(script: String): Result<String> {
        return suspendCancellableCoroutine { cont ->
            continuation = cont
            webView.evaluateJavascript(script, {})
        }
    }

    fun updateBalances(accountId: String, balancesToUpdate: Map<String, BigInteger>) {
        val balanceEntities = balancesToUpdate.map { (slug, balance) ->
            Log.d("updateBalances", "Updated balances: $slug $balance")
            BalanceEntity(accountId, slug, balance)
        }
        CoroutineScope(Dispatchers.IO).launch {
            balanceDao.insertBalances(balanceEntities)
        }
    }

    fun updateTokens(tokens: List<TokenDto>) {
        val tokenEntities = tokens.map { it.toEntity() }
        Log.d("updateTokens", "Updated tokens: $tokenEntities")

        CoroutineScope(Dispatchers.IO).launch {
            tokenDao.insertTokens(tokenEntities)
        }
    }

    // TODO callApi('getWalletInfo', network, fromAddress!),
    // callApi('getWalletInfo', "$network", "$currentAccountAddress")

    // TODO getAccountNewestTxId(accountId: string)
    // TODO getAccountTransactionSlice(accountId: string)
    // TODO getTokenTransactionSlice(accountId: string)
    // TODO fetchTokenActivitySlice
    // TODO fetchAllActivitySlice
    // TODO buildTokenSlug
    // TODO fetchToken
    // TODO fetchDieselState

    private inner class WebAppInterface {
        @JavascriptInterface
        fun onApiResult(result: String) {
            Log.d(TAG, result)
            continuation?.resume(Result.success(result))
            continuation = null
        }

        @JavascriptInterface
        fun onApiUpdate(updateJson: String) {
            Log.d(TAG, updateJson)
            val json = Json { ignoreUnknownKeys = true }
            when (val update = json.decodeFromString(ApiUpdate.serializer(), updateJson)) {
                is ApiUpdate.Balances -> {
                    val balancesToUpdate = update.balancesToUpdate.mapValues { (_, balanceString) ->
                        BigInteger(balanceString)
                    }

                    updateBalances(
                        update.accountId,
                        balancesToUpdate
                    )
                }

                is ApiUpdate.Tokens -> {
                    updateTokens(update.tokens.values.toList())
                }
            }
        }
    }

    companion object {
        const val TAG = "BlockchainRepositoryWebViewImpl"
    }
}
