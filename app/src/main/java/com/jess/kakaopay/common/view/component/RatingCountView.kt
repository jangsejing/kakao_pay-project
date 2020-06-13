package com.jess.kakaopay.common.view.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.jess.kakaopay.databinding.RateCountViewBinding
import kotlinx.android.synthetic.main.rate_count_view.view.*

/**
 * 평점
 *
 * @author jess
 * @since 2020.06.12
 */
class RatingCountView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val binding = RateCountViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {

    }

    fun setRating(rate: Float) {
        tv_rate.text = rate.toString()
    }

}
