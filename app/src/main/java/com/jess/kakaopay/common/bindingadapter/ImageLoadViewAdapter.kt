package com.jess.kakaopay.common.bindingadapter

import androidx.databinding.BindingAdapter
import com.jess.kakaopay.common.extension.loadImage
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
    this.loadImage(url)
}