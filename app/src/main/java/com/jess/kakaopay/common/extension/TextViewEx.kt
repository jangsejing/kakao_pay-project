package com.jess.kakaopay.common.extension

import android.graphics.Paint
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import com.jess.kakaopay.common.util.tryCatch

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
    tryCatch {
        val startIndex = original.indexOf(start)
        val endIndex = original.indexOf(end)
        if (startIndex > -1 && endIndex > -1) {
            // start, end 사이 문자 추출
            val output = original.substring(startIndex + start.length, endIndex)
            val replace = original.replace(start, "").replace(end, "")
            val accentStart = replace.indexOf(output)

            val convert = SpannableString(replace).apply {
                setSpan(
                    ForegroundColorSpan(color),
                    accentStart,
                    accentStart + output.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
            }
            this.text = convert
        } else {
            this.text = original
        }
        return
    }
    this.text = original
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
    tryCatch {
        var convert: String = original
        oldValue
            .split(",")
            .filter { it.isNotBlank() }
            .map {
                convert = convert.replace(it, newValue)
            }
            .toString()
        this.text = convert
        return
    }
    this.text = original
}

/**
 * 밑줄
 */
fun TextView.underLine() {
    this.paintFlags = this.paintFlags or Paint.UNDERLINE_TEXT_FLAG
}


