package com.jess.kp.common.extension

import android.webkit.WebView
import androidx.databinding.BindingAdapter

/**
 * WebView URL Load
 */
fun WebView.loadWeb(url: String?) {
    if (url.isNullOrEmpty()) {
        return
    }
    this.loadUrl(url)
}