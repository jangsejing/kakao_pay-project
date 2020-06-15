package com.jess.kakaopay.common.view.component

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.jess.kakaopay.R
import com.jess.kakaopay.common.extension.setRipple
import com.jess.kakaopay.common.extension.underLine
import com.jess.kakaopay.databinding.RolePartitionViewBinding
import kotlinx.android.synthetic.main.role_partition_view.view.*
import timber.log.Timber


/**
 * 감독, 배우 정보를 보여주기 위한 View
 *
 * @author jess
 * @since 2020.06.12
 */
class RolePartitionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val binding = RolePartitionViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {

    }

    /**
     * 역할
     */
    fun setRole(role: String?) {
        tv_role.text = role
    }

    /**
     * 멤버
     */
    fun setMembers(members: String?) {
        if (members.isNullOrEmpty()) {
            visibility = View.GONE
            return
        }
        visibility = View.VISIBLE

        members.split("|").filter { it.isNotEmpty() }.forEach {
            ll_member.addView(getMemberTextView(it))
        }
    }

    private fun getMemberTextView(member: String) = TextView(context).apply {
        tag = member
        text = String.format(context.getString(R.string.detail_role), member)
        textSize = 14f
        setTextColor(ContextCompat.getColor(context, R.color.brand_white))
        setPadding(
            context.resources.getDimensionPixelSize(R.dimen.dp2),
            0,
            context.resources.getDimensionPixelSize(R.dimen.dp2),
            0
        )
        setRipple()
        underLine()
        setOnClickListener {
            val url = "https://www.google.com/search?q=${it.tag}"
            Timber.d(">> $url")
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
            context.startActivity(intent)
        }
    }
}
