package com.jess.kakaopay.common.view.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
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

    fun setRating(rate: String?) {
        rate?.let {
            tv_rate.text = it
            visibility = View.VISIBLE
        } ?: run {
            visibility = View.GONE
        }
    }
}
