package ru.andvl.mytonwallet.contest.blockchain.impl

import android.util.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository
import ru.andvl.mytonwallet.contest.blockchain.util.WebViewHolder

class BlockchainRepositoryWebViewImpl(
    private val webView: WebViewHolder
): BlockchainRepository {
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

    companion object {
        const val TAG = "BlockchainRepositoryWebViewImpl"
    }
}
