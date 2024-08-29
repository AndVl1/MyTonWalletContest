package ru.andvl.mytonwallet.contest.blockchain.impl

import android.util.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository
import ru.andvl.mytonwallet.contest.blockchain.util.WebViewHolder
import kotlin.coroutines.resumeWithException

class BlockchainRepositoryWebViewImpl(
    private val webView: WebViewHolder
): BlockchainRepository {
    private var isApiInitialized = false

    override suspend fun initApi() {
        webView.evaluateJavascript("initApi()", {})
        isApiInitialized = true
    }

    override suspend fun createWallet(): Result<String> {
        return evaluateJs("startCreatingWallet") // TODO idk how is correct :)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getMnemonicWordList(): Result<List<String>> {
        ensureApiInitialized()
        return runCatching {
            suspendCancellableCoroutine { continuation ->
                webView.evaluateJavascript("callApi('getMnemonicWordList')") { result ->
                    if (result != null && result != "null") {
                        try {
                            // Убираем двойные кавычки, если есть
                            val cleanedResult = result.trim('"')

                            // Парсим JSON в JsonElement
                            val jsonElement: JsonElement = Json.parseToJsonElement(cleanedResult)

                            // Проверяем, является ли JsonElement массивом
                            if (jsonElement is JsonArray) {
                                val wordList = jsonElement.mapNotNull {
                                    (it as? JsonPrimitive)?.contentOrNull
                                }
                                continuation.resume(wordList) { cause ->
                                    Log.e(TAG, "evaluation error:", cause)
                                }
                            } else {
                                continuation.resumeWithException(IllegalStateException("Expected JSON array"))
                            }
                        } catch (e: Exception) {
                            continuation.resumeWithException(e)
                        }
                    } else {
                        continuation.resumeWithException(NullPointerException("JavaScript returned null or empty result"))
                    }
                }
            }
        }
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
