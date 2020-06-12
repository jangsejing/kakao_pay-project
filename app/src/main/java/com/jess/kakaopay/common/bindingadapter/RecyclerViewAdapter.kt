package com.jess.kakaopay.common.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jess.kakaopay.common.extension.addItems

/**
 * @author jess
 * @since 2020.06.12
 */

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