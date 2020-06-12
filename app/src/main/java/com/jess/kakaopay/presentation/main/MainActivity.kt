package com.jess.kakaopay.presentation.main

import android.os.Bundle
import com.jess.kakaopay.R
import com.jess.kakaopay.common.base.BaseActivity
import com.jess.kakaopay.common.base.BaseRecyclerViewAdapter
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.databinding.MainActivityBinding
import com.jess.kakaopay.databinding.MainItemBinding
import kotlinx.android.synthetic.main.main_activity.*
import timber.log.Timber

/**
 * @author jess
 * @since 2020.06.12
 */
class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>() {

    override val layoutRes = R.layout.main_activity

    override val viewModelClass = MainViewModel::class.java

    override fun initLayout() {
        rv_movie.adapter = object :
            BaseRecyclerViewAdapter<MovieData, MainItemBinding>(R.layout.main_item) {
        }.apply {
            setOnItemClickListener { view, item ->

            }
        }
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }
}
