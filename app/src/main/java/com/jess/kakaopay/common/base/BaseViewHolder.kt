package com.jess.kakaopay.common.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jess.kakaopay.common.util.tryCatch

/**
 * @author jess
 * @since 2020.06.12
 */
open class BaseViewHolder<T : Any?>(
    val viewDataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(viewDataBinding.root) {

    val view = viewDataBinding.root

    open fun onBind(item: T?) {
        tryCatch {
//            viewDataBinding.setVariable(BR.item, item)
            viewDataBinding.executePendingBindings()
        }
    }
}