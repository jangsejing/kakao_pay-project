package com.jess.kakaopay.common.extension

import com.jess.kakaopay.common.view.component.ImageLoadView

/**
 * @author jess
 * @since 2020.06.12
 */

/**
 * 이미지 로드
 */
fun ImageLoadView.loadImage(
    url: String?
) {
    if (!url.isNullOrEmpty()) {
        this.load(url)
    }
}