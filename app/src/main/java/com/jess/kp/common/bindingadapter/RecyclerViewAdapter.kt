package com.jess.kp.common.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jess.kp.common.extension.addItems

/**
 * RecyclerView Adapter
 *
 * @param items
 * @param isClear
 */
@BindingAdapter(value = ["items", "isClear"], requireAll = false)
fun RecyclerView.addItems(
    items: List<Any>?,
    isClear: Boolean = true
) {
    this.addItems(items, isClear)
}