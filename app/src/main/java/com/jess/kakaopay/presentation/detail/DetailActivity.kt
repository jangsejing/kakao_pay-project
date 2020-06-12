package com.jess.kakaopay.presentation.detail

import android.os.Bundle
import com.jess.kakaopay.R
import com.jess.kakaopay.common.base.BaseActivity
import com.jess.kakaopay.common.base.BaseViewModel
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.databinding.DetailActivityBinding
import com.jess.kakaopay.databinding.MainActivityBinding
import com.jess.kakaopay.presentation.main.MainViewModel

/**
 * @author jess
 * @since 2020.06.12
 */
class DetailActivity : BaseActivity<DetailActivityBinding, DetailViewModel>() {

    companion object {
        const val EXTRA_MOVIE_DATA = "EXTRA_MOVIE_DATA"
    }

    override val layoutRes = R.layout.detail_activity

    override val viewModelClass = DetailViewModel::class.java

    private val movieData by lazy {
        intent?.getSerializableExtra(EXTRA_MOVIE_DATA) as? MovieData.Item
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {
        viewModel.setData(movieData)
    }
}
