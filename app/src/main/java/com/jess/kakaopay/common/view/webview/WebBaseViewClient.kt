package com.jess.kakaopay.common.view.webview

import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jess.kakaopay.R

/**
 * @author jess
 * @description WebChromeClient for WebView
 */
class WebBaseViewClient(private val context: Context?) : WebViewClient() {

    private var onPageFinishedListener: (() -> Unit)? = null

    fun setOnPageFinishedListener(listener: (() -> Unit)?) {
        onPageFinishedListener = listener
    }

    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView, url: String) {
        onPageFinishedListener?.invoke()
        super.onPageFinished(view, url)
    }

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        return super.shouldOverrideUrlLoading(view, url)
    }

    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        return shouldOverrideUrlLoading(view, request.url.toString())
    }

    override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
        AlertDialog.Builder(context).run {
            setTitle(R.string.web_ssl_error)
            setPositiveButton(
                context.getString(android.R.string.ok)
            ) { _, _ ->
                handler.proceed()
            }
            setNegativeButton(
                context.getString(android.R.string.cancel)
            ) { _, _ ->
                handler.cancel()
            }
            setCancelable(false)
            show()
        }
    }
}
