package com.jess.kakaopay.common.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.jess.kakaopay.R

/**
 * @author jess
 * @since 2020.06.12
 */

/**
 * 이미지 로드
 */
fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) {
        return
    }

    Glide.with(this)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .placeholder(R.drawable.shp_rect_f5f5f5_r12)
        .error(R.drawable.shp_rect_f5f5f5_r12)
        .apply(
            RequestOptions().transform(
                CenterCrop(), RoundedCorners(
                    context.resources.getDimensionPixelSize(
                        R.dimen.dp12
                    )
                )
            )
        )
        .into(this)
}