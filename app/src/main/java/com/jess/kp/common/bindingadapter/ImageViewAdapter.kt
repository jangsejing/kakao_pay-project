package com.jess.kp.common.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.jess.kp.common.extension.loadImage

/**
 * 이미지 로드
 */
@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    this.loadImage(url)
}