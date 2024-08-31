package ru.andvl.mytonwallet.contest.blockchain.impl

import android.util.Log
import android.webkit.JavascriptInterface
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository
import ru.andvl.mytonwallet.contest.blockchain.util.WebViewHolder
import kotlin.coroutines.resume

class BlockchainRepositoryWebViewImpl(
    private val webView: WebViewHolder
) : BlockchainRepository {
    private var isApiInitialized = false

    init {
        webView.addJavascriptInterface(WebAppInterface())
    }

    private var continuation: CancellableContinuation<Result<String>>? = null

    override suspend fun getMnemonicWordList(): List<String> {
        val jsonString = evaluateJs("callApi('getMnemonicWordList')").getOrThrow()

        val jsonElement = Json.parseToJsonElement(jsonString)
        return jsonElement.jsonArray.map { it.jsonPrimitive.content }
    }

    override suspend fun initApi() {
        webView.evaluateJavascript("initApi()", {})
        isApiInitialized = true
    }

    override suspend fun createWallet(): Result<String> {
        return evaluateJs("startCreatingWallet") // TODO idk how is correct :)
    }

    private suspend fun evaluateJs(script: String): Result<String> {
        return suspendCancellableCoroutine { cont ->
            continuation = cont
            webView.evaluateJavascript(script, {})
        }
    }

    private suspend fun ensureApiInitialized() {
        if (!isApiInitialized) {
            initApi()
        }
    }


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
