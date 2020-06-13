package com.jess.kakaopay.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.jess.kakaopay.R
import com.jess.kakaopay.common.base.BaseActivity
import com.jess.kakaopay.common.base.BaseRecyclerViewAdapter
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.databinding.MainActivityBinding
import com.jess.kakaopay.databinding.MainItemBinding
import com.jess.kakaopay.presentation.detail.DetailActivity
import com.jess.kakaopay.presentation.detail.DetailActivity.Companion.EXTRA_MOVIE_DATA
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.search_view.*
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
            BaseRecyclerViewAdapter<MovieData.Item, MainItemBinding>(R.layout.main_item) {
        }.apply {
            setOnItemClickListener { view, item ->
                val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra(EXTRA_MOVIE_DATA, item)
                }
                startActivity(intent)
            }
        }

        cv_search.seOnTextListener {
            viewModel.getMovie(it)
        }

        et_search.setText("베트맨")
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun onResume() {
        super.onResume()
        cv_search.onResume()
    }

    override fun onPause() {
        cv_search.onPause()
        super.onPause()
    }

    override fun initDataBinding() {
        super.initDataBinding()
        viewModel.run {
        }
    }
}
