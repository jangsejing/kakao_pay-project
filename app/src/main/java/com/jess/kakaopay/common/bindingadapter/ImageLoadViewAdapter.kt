package com.jess.kakaopay.common.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jess.kakaopay.common.extension.loadImage
import com.jess.kakaopay.common.view.component.ImageLoadView

/**
 * @author jess
 * @since 2020.06.12
 */

/**
 * 이미지 로드
 */
@BindingAdapter(value = ["url", "isCorners"], requireAll = false)
fun ImageLoadView.loadImage(
    url: String?,
    isCorners: Boolean = false
) {
    this.loadImage(url, isCorners)
}

/**
 * 이미지 로드
 */
@BindingAdapter("url")
fun ImageView.loadImage(
    url: String?
) {
    Glide.with(this)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .into(this)
}