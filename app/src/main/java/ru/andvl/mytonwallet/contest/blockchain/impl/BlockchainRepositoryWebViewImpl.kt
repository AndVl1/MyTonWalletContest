package ru.andvl.mytonwallet.contest.blockchain.impl

import android.util.Log
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository
import ru.andvl.mytonwallet.contest.blockchain.util.WebViewHolder
import kotlin.coroutines.resume

@OptIn(ExperimentalCoroutinesApi::class)
class BlockchainRepositoryWebViewImpl(
    private val webView: WebViewHolder
): BlockchainRepository {
    private var isApiInitialized = false

    init {
        webView.addJavascriptInterface { result ->
            continuation?.resume(Result.success(result))
            continuation = null
        }
    }

    private var continuation: CancellableContinuation<Result<String>>? = null

    override suspend fun getMnemonicWordList(): Result<String> {
        return suspendCancellableCoroutine { cont ->
            continuation = cont // Сохраняем continuation

            webView.evaluateJavascript("callApi('getMnemonicWordList')") { result ->
                if (result == null) {
                    cont.resume(Result.failure(Exception("Error evaluating JavaScript"))) // Возвращаем ошибку
                } else {
                    cont.resume(Result.success(result)) // Возвращаем успешный результат
                }
            }
        }
    }

    override suspend fun initApi() {
        webView.evaluateJavascript("initApi()", {})
        isApiInitialized = true
    }

    override suspend fun createWallet(): Result<String> {
        return evaluateJs("startCreatingWallet") // TODO idk how is correct :)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun evaluateJs(script: String): Result<String> {
        return runCatching {
            suspendCancellableCoroutine { continuation ->
                webView.evaluateJavascript(script) { result ->
                    continuation.resume(result.orEmpty()) { cause ->
                        Log.e(TAG, "evaluation error:", cause)
                    }
                }
            }
        }
    }

    private suspend fun ensureApiInitialized() {
        if (!isApiInitialized) {
            initApi()
        }
    }

    companion object {
        const val TAG = "BlockchainRepositoryWebViewImpl"
    }
}
