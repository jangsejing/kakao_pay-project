package com.jess.kp.common.view.webview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.webkit.CookieManager
import android.webkit.WebSettings

/**
 * WebView
 *
 * @author jess
 * @since 2020.06.12
 */
class WebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : android.webkit.WebView(context, attrs, defStyleAttr) {

    init {
        initView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView() {
        settings.run {
            javaScriptEnabled = true
            useWideViewPort = true
            databaseEnabled = true
            domStorageEnabled = true
            cacheMode = WebSettings.LOAD_NO_CACHE
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        // Setting - Cookie Policy
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptThirdPartyCookies(this, true)

        // WebViewClient
        webViewClient = WebBaseViewClient(context)
    }

}