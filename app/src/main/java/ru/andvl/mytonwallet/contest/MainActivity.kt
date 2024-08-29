package ru.andvl.mytonwallet.contest

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.stack.animation.LocalStackAnimationProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.compose.koinViewModel
import ru.andvl.mytonwallet.contest.blockchain.api.BlockchainRepository
import ru.andvl.mytonwallet.contest.root.api.LocalRootNavigation
import ru.andvl.mytonwallet.contest.root.api.RootDecomposeComponent
import ru.andvl.mytonwallet.contest.ui.components.TonWalletButton
import ru.andvl.mytonwallet.contest.ui.theme.MyTonWalletContestTheme
import ru.andvl.mytonwallet.contest.ui.utils.WalletStackAnimationProvider

class MainActivity : ComponentActivity() {
    private val rootComponentFactory: RootDecomposeComponent.Factory = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val root = rootComponentFactory.invoke(defaultComponentContext(), this::finish)
        setContent {
            MyTonWalletContestTheme {
                CompositionLocalProvider(
                    LocalRootNavigation provides root,
                    LocalStackAnimationProvider provides WalletStackAnimationProvider
                ) {
//                    root.Render(
//                        Modifier.fillMaxSize()
//                            .imePadding()
//                    )
                    Test(
                        modifier = Modifier
                            .fillMaxSize()
                            .imePadding()
                    )
//                    WebViewComponent()
                }
            }
        }
    }
}

class BlockchainViewModel(
    private val blockchainRepository: BlockchainRepository
) : ViewModel() {
    private val _state = MutableStateFlow(emptyList<String>())
    val state = _state.asStateFlow()

    fun onClicked() {
        viewModelScope.launch {
            val result = blockchainRepository.getMnemonicWordList()
            Log.e("BlockchainViewModel", "result: $result")
            if (result.isSuccess) {
                _state.update { result.getOrDefault(emptyList()) }
            }
        }
    }
}

@Composable
fun Test(modifier: Modifier = Modifier) {
    val viewModel: BlockchainViewModel = koinViewModel()
    val state = viewModel.state.collectAsState().value

    // Collect the flow as state, with an initial empty list
    Column(modifier) {
        TonWalletButton(
            onClick = viewModel::onClicked,
            text = "Click",
        )
        LazyColumn {
            items(state) {
                Text(text = it)
            }
        }
    }
}


//@SuppressLint("SetJavaScriptEnabled")
//@Composable
//fun WebViewComponent() {
//    val context = LocalContext.current
//    val webView = remember { WebView(context) }
//    Column(modifier = Modifier.fillMaxSize()) {
//        TonWalletButton(
//            onClick = { callJavaScriptFunction(webView, "callApi", "'getMnemonicWordList'") },
//            text = "Click",
//            modifier = Modifier.weight(1f)
//        )

//        AndroidView(
//            factory = {
//                webView.apply {
//                    settings.javaScriptEnabled = true
//                    settings.domStorageEnabled = true
//                    settings.allowFileAccess = true
//                    settings.allowContentAccess = true
//                    settings.allowUniversalAccessFromFileURLs = true
//                    settings.allowFileAccessFromFileURLs = true
//
////                addJavascriptInterface(WebAppInterface(context), "AndroidInterface")
//
//                    webViewClient = object : WebViewClient() {
//                        override fun onPageFinished(view: WebView, url: String) {
//                            super.onPageFinished(view, url)
//                            // Call JavaScript function after page is loaded
//                            callJavaScriptFunction(view, "initApi")
////                        callJavaScriptFunction(view, "callApi", "'getMnemonicWordList'")
//                        }
//                    }
//
//                    webChromeClient = WebChromeClient()
//                    loadUrl("file:///android_asset/index.html")
//                }
//            },
//            modifier = Modifier
//        )
//}
//}

fun callJavaScriptFunction(
    webView: WebView,
    functionName: String,
    vararg params: String
) {
    val paramStr = params.joinToString(",")
    val jsCode = "$functionName($paramStr);"

    webView.evaluateJavascript(jsCode) {
        println(it)
    }
}

//class WebViewBlockchainRepository(
//    private val webViewHolder: WebViewHolder
//) {
//    @OptIn(ExperimentalCoroutinesApi::class)
//    private suspend fun executeScript(script: String): String? {
//        return suspendCancellableCoroutine { continuation ->
//            webViewHolder.evaluateJavascript(script) { result ->
//                continuation.resume(result) {}
//            }
//        }
//    }
//
//    suspend fun getMnemonicWordList(): Result<Double> {
//        val script = "callApi('getMnemonicWordList')"
//        return try {
//            val result = executeScript(script)?.toDoubleOrNull()
//            if (result != null) {
//                Result.success(result)
//            } else {
//                Result.failure(Exception("Invalid result"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//}

//class WebAppInterface(private val context: Context) {
//    @JavascriptInterface
//    fun showToast(message: String) {
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//    }
//
//    @JavascriptInterface
//    fun onApiResult(result: String) {
//        // Обработка результата из JavaScript
//        println("Received API result from JavaScript: $result")
//    }
//}