package ru.andvl.mytonwallet.contest.blockchain.impl

import android.util.Log
import android.webkit.JavascriptInterface
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository
import ru.andvl.mytonwallet.contest.blockchain.impl.dto.AuthResultDto
import ru.andvl.mytonwallet.contest.blockchain.util.MNEMONIC_CHECK_COUNT
import ru.andvl.mytonwallet.contest.blockchain.util.MNEMONIC_COUNT
import ru.andvl.mytonwallet.contest.blockchain.util.WebViewHolder
import ru.andvl.mytonwallet.contest.bottombar.impl.model.AssetToken
import kotlin.coroutines.resume

class BlockchainRepositoryWebViewImpl(
    private val webView: WebViewHolder
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

    override suspend fun getCurrentAccountTokenBalances(): List<AssetToken> {
        if (currentAccountId != null) {
            val accountIdJson = Json.encodeToString(currentAccountId)
            val jsonString =
                evaluateJs("callApi('getAccountTokenBalances', $accountIdJson)").getOrThrow()
            return listOf()
        } else {
            return emptyList()
        }
    }

    private suspend fun evaluateJs(script: String): Result<String> {
        return suspendCancellableCoroutine { cont ->
            continuation = cont
            webView.evaluateJavascript(script, {})
        }
    }

    // TODO getAccountNewestTxId(accountId: string)
    // TODO getAccountTransactionSlice(accountId: string)
    // TODO getTokenTransactionSlice(accountId: string)
    // TODO getAccountBalance(accountId: string)

    private inner class WebAppInterface {
        @JavascriptInterface
        fun onApiResult(result: String) {
            Log.d(TAG, result)
            continuation?.resume(Result.success(result))
            continuation = null
        }
    }

    companion object {
        const val TAG = "BlockchainRepositoryWebViewImpl"
    }
}
