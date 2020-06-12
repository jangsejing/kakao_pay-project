package com.jess.kakaopay.common.extension

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jess.kakaopay.common.base.BaseRecyclerViewAdapter
import com.jess.kakaopay.common.util.tryCatch

/**
 * RecyclerView Adapter
 *
 * @param items
 * @param isClear
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter(value = ["items", "isClear"], requireAll = false)
fun RecyclerView.addItems(
    items: List<Any>?,
    isClear: Boolean = true
) {
    tryCatch {
        (this.adapter as? BaseRecyclerViewAdapter<Any, ViewDataBinding>)?.run {
            if (isClear) {
                this.clear()
            }

            if (!items.isNullOrEmpty()) {
                this.addItems(items)
            }
        }
    }
}