package ru.andvl.mytonwallet.contest.blockchain.util

import android.annotation.SuppressLint
import android.content.Context
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

        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)

                evaluateJavascript("initApi()") { }
            }
        }

        webChromeClient = WebChromeClient()
        loadUrl("file:///android_asset/index.html")
    }

    fun getWebView(): WebView? = webView

    @SuppressLint("JavascriptInterface")
    fun addJavascriptInterface(jsInterface: Any) {
        webView?.addJavascriptInterface(jsInterface, "AndroidInterface")
    }

    fun evaluateJavascript(script: String, callback: (String?) -> Unit) {
        webView?.evaluateJavascript(script, callback)
    }

    fun destroy() {
        webView?.destroy()
        webView = null
    }
}
