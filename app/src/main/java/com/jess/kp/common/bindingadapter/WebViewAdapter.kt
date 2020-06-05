package com.jess.kp.common.bindingadapter

import android.webkit.WebView
import androidx.databinding.BindingAdapter
import com.jess.kp.common.extension.loadWeb

/**
 * @author jess
 * @since 2020.06.12
 */

/**
 * WebView URL Load
 */
@BindingAdapter("loadWeb")
fun WebView.loadWeb(url: String?) {
    this.loadWeb(url)
}