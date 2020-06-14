package com.jess.kakaopay.common.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jess.kakaopay.common.util.tryCatch
import timber.log.Timber

class BoundRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var listener: (() -> Unit)? = null
    private var lastVisiblePosition = -1
    private var isLoading = false

    init {
        initialize()
    }

    fun setOnBoundListener(listener: (() -> Unit)?) {
        this.listener = listener
    }

    private fun initialize() {


        /**
         * RecyclerView.OnScrollListener
         */
        addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                layoutManager?.let { layoutManager ->
                    val manager = layoutManager as LinearLayoutManager
                    if (!isLoading) {
                        val totalItemCount = manager.itemCount
                        if (totalItemCount == 0 || dy == 0) {
                            return
                        }

                        if (!isLoading && totalItemCount <= lastVisiblePosition + 2) {
                            listener?.let {
                                Timber.d(">> Bound")
                                isLoading = true
                                it.invoke()
                            }
                        }
                    }
                }

            }
        })
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        if (adapter != null) {
            tryCatch {
                adapter.registerAdapterDataObserver(object :
                    RecyclerView.AdapterDataObserver() {
                    override fun onChanged() {
                        super.onChanged()
                        isLoading = false
                    }
                })
            }
        }
        super.setAdapter(adapter)
    }
}
