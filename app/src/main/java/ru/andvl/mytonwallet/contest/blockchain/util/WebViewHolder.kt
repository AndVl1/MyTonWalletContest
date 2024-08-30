package ru.andvl.mytonwallet.contest.blockchain.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewHolder(context: Context) {
    @SuppressLint("SetJavaScriptEnabled")
    private var webView: WebView? = WebView(context).apply {
        settings.javaScriptEnabled = true

        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.allowFileAccess = true
        settings.allowContentAccess = true
        settings.allowUniversalAccessFromFileURLs = true
        settings.allowFileAccessFromFileURLs = true

//        webViewClient = WebViewClient()
        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)

                evaluateJavascript("initApi()", {})
//                evaluateJavascript( "callApi('getMnemonicWordList')", {})
            }
        }

        webChromeClient = WebChromeClient()
        loadUrl("file:///android_asset/index.html")
    }

    fun getWebView(): WebView? = webView

    fun addJavascriptInterface(callback: (String) -> Unit) {
        webView?.addJavascriptInterface(WebAppInterface(callback), "AndroidInterface")
    }

    fun evaluateJavascript(script: String, callback: (String?) -> Unit) {
        webView?.evaluateJavascript(script, callback)
    }

    fun destroy() {
        webView?.destroy()
        webView = null
    }

    private class WebAppInterface(private val callback: (String) -> Unit) {

        @JavascriptInterface
        fun onApiResult(result: String) {
            Log.d(TAG, result)
            callback(result)
        }
    }

    companion object {
        private const val TAG = "WebViewHolder"
    }
}
