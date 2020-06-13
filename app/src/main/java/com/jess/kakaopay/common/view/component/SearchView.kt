package com.jess.kakaopay.common.view.component

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import android.widget.TextView
import com.jess.kakaopay.R
import com.jess.kakaopay.common.util.DeviceUtils
import com.jess.kakaopay.databinding.SearchViewBinding
import kotlinx.android.synthetic.main.search_view.view.*

/**
 * 검색 뷰
 *
 * @author jess
 * @since 2020.06.13
 */
class SearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), View.OnClickListener {

    private val binding = SearchViewBinding.inflate(LayoutInflater.from(context), this, true)
    private var listener: ((String?) -> Unit)? = null

    init {
        initLayout()
    }

    private fun initLayout() {

        binding.isDelete = true

        cl_delete.setOnClickListener(this)
        et_search.run {
            addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    listener?.invoke(s.toString())
                    binding.isDelete = count < 1
                }
            })

            setOnEditorActionListener(object : TextView.OnEditorActionListener {
                override fun onEditorAction(
                    v: TextView?,
                    actionId: Int,
                    event: KeyEvent?
                ): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        listener?.invoke(et_search.text.toString())
                        return true
                    }
                    return false
                }
            })
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cl_delete -> et_search.setText("")
        }
    }

    fun onResume() {
        showKeyboard(true)
    }

    fun onPause() {
        showKeyboard(false)
    }

    /**
     * EditText, 키보드 처리
     */
    private fun showKeyboard(isShow: Boolean) {
        et_search?.let {
            if (isShow) {
                it.requestFocus()
                DeviceUtils.showKeyboard(it)
            } else {
                it.clearFocus()
                DeviceUtils.hideKeyboard(it)
            }
        }
    }

    /**
     * 텍스트 리스너
     */
    fun seOnTextListener(listener: ((String?) -> Unit)?) {
        this.listener = listener
    }
}
