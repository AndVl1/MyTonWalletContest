package ru.andvl.mytonwallet.contest.blockchain.util

import android.content.Context
import android.webkit.WebView

class WebViewHolder(context: Context) {
    private var webView: WebView? = WebView(context).apply {
        settings.javaScriptEnabled = true
    }

    fun getWebView(): WebView? = webView

    fun evaluateJavascript(script: String, callback: (String?) -> Unit) {
        webView?.evaluateJavascript(script, callback)
    }

    fun destroy() {
        webView?.destroy()
        webView = null
    }
}
