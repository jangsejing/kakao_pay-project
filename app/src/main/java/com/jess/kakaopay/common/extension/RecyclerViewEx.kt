package com.jess.kakaopay.common.extension

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jess.kakaopay.common.base.BaseListAdapter
import com.jess.kakaopay.common.base.BaseRecyclerViewAdapter
import com.jess.kakaopay.common.util.tryCatch

/**
 * RecyclerView Adapter
 *
 * @param items
 * @param isClear
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter(value = ["addItems", "isClear"], requireAll = false)
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


/**
 * RecyclerView Adapter
 *
 * @param items
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("addItems")
fun RecyclerView.submitList(
    items: List<Any>?
) {
    tryCatch {
        (this.adapter as? BaseListAdapter<Any>)?.run {
            if (!items.isNullOrEmpty()) {
                this.submitList(items)
            }
        }
    }
}
