package ru.andvl.mytonwallet.contest

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.viewinterop.AndroidView
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.stack.animation.LocalStackAnimationProvider
import org.koin.android.ext.android.get
import ru.andvl.mytonwallet.contest.root.api.LocalRootNavigation
import ru.andvl.mytonwallet.contest.root.api.RootDecomposeComponent
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
                    WebViewScreen()
                }
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen() {
    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()

            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            settings.allowUniversalAccessFromFileURLs = true
            settings.allowFileAccessFromFileURLs = true

            loadUrl("file:///android_asset/index.html")
        }
    }
    )
}
