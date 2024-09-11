package ru.andvl.mytonwallet.contest.blockchain.impl

import android.util.Log
import android.webkit.JavascriptInterface
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import ru.andvl.mytonwallet.contest.R
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.ApiUpdate
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.AuthResultDto
import ru.andvl.mytonwallet.contest.blockchain.impl.dtos.TokenDto
import ru.andvl.mytonwallet.contest.blockchain.util.MNEMONIC_CHECK_COUNT
import ru.andvl.mytonwallet.contest.blockchain.util.MNEMONIC_COUNT
import ru.andvl.mytonwallet.contest.blockchain.util.MYCOIN_SLUG
import ru.andvl.mytonwallet.contest.blockchain.util.TONCOIN_SLUG
import ru.andvl.mytonwallet.contest.blockchain.util.WebViewHolder
import ru.andvl.mytonwallet.contest.bottombar.impl.model.AssetToken
import ru.andvl.mytonwallet.contest.bottombar.impl.model.AssetTokenType
import ru.andvl.mytonwallet.contest.bottombar.impl.model.TokenImage
import ru.andvl.mytonwallet.contest.database.daos.BalanceDao
import ru.andvl.mytonwallet.contest.database.daos.StakingStateDao
import ru.andvl.mytonwallet.contest.database.daos.TokenDao
import ru.andvl.mytonwallet.contest.database.entities.BalanceEntity
import ru.andvl.mytonwallet.contest.database.entities.StakingStateEntity
import ru.andvl.mytonwallet.contest.datastore.UserSettingsRepository
import ru.andvl.mytonwallet.contest.mappers.toEntity
import java.math.BigInteger
import kotlin.coroutines.resume

class BlockchainRepositoryWebViewImpl(
    private val webView: WebViewHolder,
    private val userSettingsRepository: UserSettingsRepository,
    private val balanceDao: BalanceDao,
    private val tokenDao: TokenDao,
    private val stakingStateDao: StakingStateDao
) : BlockchainRepository {
    private var currentAccountId: String? = null
    private var currentAccountAddress: String? = null
    private val network: String = "mainnet"

    init {
        webView.addJavascriptInterface(WebAppInterface())

        CoroutineScope(Dispatchers.Main).launch {
            userSettingsRepository.getWalletAccountId().collect {
                currentAccountId = it
            }

            userSettingsRepository.getWalletAddress().collect {
                currentAccountAddress = it
            }
        }
    }

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

    override suspend fun updateCurrentAccountId(id: String) {
        userSettingsRepository.updateWalletAccountId(id)
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
            userSettingsRepository.updateWalletAccountId(result.accountId)
            currentAccountAddress = result.address
            userSettingsRepository.updateWalletAddress(result.address)
        }
    }

    override suspend fun activateAccount() {
        val accountId = userSettingsRepository.getWalletAccountId().first()

        evaluateJs(
            """
            callApi('activateAccount', "$accountId")
            """.trimIndent()
        ).getOrThrow()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getCurrentAccountAssetTokens(): Flow<List<AssetToken>> {
        val accountId = userSettingsRepository.getWalletAccountId().first()

        return getCurrentAccountBalances().flatMapConcat { balances ->
            val assetTokensFlows = balances.map { balance ->
                tokenDao.getTokenBySlug(balance.slug).map { token ->
                    val stakingState = stakingStateDao.getStakingStateByAccountId(accountId)

                    AssetToken(
                        type = when (token.slug) {
                            TONCOIN_SLUG -> {
                                AssetTokenType.STACKED
                            }

                            MYCOIN_SLUG -> {
                                AssetTokenType.VESTED
                            }

                            else -> {
                                AssetTokenType.SIMPLE
                            }
                        },
                        slug = balance.slug,
                        name = token.name,
                        image = if (token.slug == TONCOIN_SLUG) {
                            TokenImage.Resource(R.drawable.toncoin)
                        } else {
                            token.image?.let { TokenImage.Url(it) }
                        },
                        amount = balance.balance.toBigDecimal().movePointLeft(token.decimals),
                        amountUsd = balance.balance.toBigDecimal()
                            .movePointLeft(token.decimals) * token.priceUsd.toBigDecimal(),
                        price = token.price.toFloat(),
                        symbol = token.symbol,
                        change = token.percentChange24h.toFloat(),
                        apy = if (token.slug == TONCOIN_SLUG) stakingState?.apy else null
                    )
                }
            }
            combine(assetTokensFlows) { assetTokensArray ->
                assetTokensArray.toList()
            }
        }
    }

    override suspend fun getCurrentAccountBalances(): Flow<List<BalanceEntity>> {
        currentAccountId?.let {
            return balanceDao.getBalancesForAccount(it)
        } ?: throw NoSuchElementException()
    }

    override suspend fun getCurrentAccountWalletBalance(): BigInteger {
        val address = userSettingsRepository.getWalletAddress().first()

        val jsonString = evaluateJs(
            """
            callApi('getWalletBalance', "$network", "$address")
            """.trimIndent()
        ).getOrThrow()

        return BigInteger(Json.decodeFromString<String>(jsonString))
    }

    private suspend fun evaluateJs(script: String): Result<String> {
        return suspendCancellableCoroutine { cont ->
            continuation = cont
            webView.evaluateJavascript(script) {}
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

    fun updateStaking(update: ApiUpdate.Stacking) {
        val newStakingState: StakingStateEntity = update.toEntity()

        CoroutineScope(Dispatchers.IO).launch {
            stakingStateDao.updateStakingState(newStakingState)
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
                    val balancesToUpdate =
                        update.balancesToUpdate.mapValues { (_, balanceString) ->
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

                is ApiUpdate.Stacking -> updateStaking(update)
            }
        }
    }

    companion object {
        const val TAG = "BlockchainRepositoryWebViewImpl"
    }
}
