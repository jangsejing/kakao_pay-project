package com.jess.kakaopay.common.extension

import androidx.databinding.BindingAdapter
import com.jess.kakaopay.common.view.component.ImageLoadView

/**
 * @author jess
 * @since 2020.06.12
 */

/**
 * 이미지 로드
 */
@BindingAdapter("url")
fun ImageLoadView.loadImage(
    url: String?
) {
    if (!url.isNullOrEmpty()) {
        this.load(url)
    }
}