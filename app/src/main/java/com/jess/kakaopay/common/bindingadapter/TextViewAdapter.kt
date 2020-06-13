package com.jess.kakaopay.common.bindingadapter

import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import com.jess.kakaopay.common.extension.accentTextColor
import com.jess.kakaopay.common.extension.replaceText

/**
 * @author jess
 * @since 2020.06.12
 */

/**
 * <b>, </b> 강조 텍스트 변환
 */
@BindingAdapter("original", "start", "end", "color")
fun TextView.accentTextColor(
    original: String,
    start: String,
    end: String,
    @ColorInt color: Int
) {
    this.accentTextColor(original, start, end, color)
}

/**
 * oldValue를 newValue로 변경
 */
@BindingAdapter("original", "oldValue", "newValue")
fun TextView.replaceText(
    original: String,
    oldValue: String,
    newValue: String
) {
    this.replaceText(original, oldValue, newValue)
}
