package com.jess.kakaopay.presentation.main

import android.content.Intent
import android.os.Bundle
import com.jess.kakaopay.R
import com.jess.kakaopay.common.base.BaseActivity
import com.jess.kakaopay.common.base.BaseRecyclerViewAdapter
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.databinding.MainActivityBinding
import com.jess.kakaopay.databinding.MainItemBinding
import com.jess.kakaopay.presentation.detail.DetailActivity
import com.jess.kakaopay.presentation.detail.DetailActivity.Companion.EXTRA_MOVIE_DATA
import kotlinx.android.synthetic.main.main_activity.*

/**
 * @author jess
 * @since 2020.06.12
 */
class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>() {

    override val layoutRes = R.layout.main_activity

    override val viewModelClass = MainViewModel::class.java

    override fun initLayout() {
        rv_movie.run {

            adapter = object : BaseRecyclerViewAdapter<MovieData.Item, MainItemBinding>(
                R.layout.main_item
            ) {

            }.apply {
                setOnItemClickListener { view, item ->
                    val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                        putExtra(EXTRA_MOVIE_DATA, item)
                    }
                    startActivity(intent)
                }
            }

            setOnPagingListener {
                viewModel.getNextPage()
            }
        }

        // 텍스트 리턴
        cv_search.seOnTextListener {
            viewModel.getMovie(it)
        }
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun onResume() {
        super.onResume()
        cv_search.onResume()
    }

    override fun onPause() {
        cv_search.onPause()
        super.onPause()
    }
}
