package com.jess.kakaopay.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.jess.kakaopay.R
import com.jess.kakaopay.common.base.BaseActivity
import com.jess.kakaopay.common.base.BaseListAdapter
import com.jess.kakaopay.data.MovieData
import com.jess.kakaopay.databinding.MainActivityBinding
import com.jess.kakaopay.presentation.detail.DetailActivity
import com.jess.kakaopay.presentation.detail.DetailActivity.Companion.EXTRA_MOVIE_DATA
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.search_view.*

/**
 * @author jess
 * @since 2020.06.12
 */
class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>() {

    override val layoutRes = R.layout.main_activity

    override val viewModelClass = MainViewModel::class.java

    override fun initLayout() {
        rv_movie.run {

            adapter = object : BaseListAdapter<MovieData.Item>(
                R.layout.main_item,
                viewModel.diffCallback
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
                viewModel.onNextPage()
            }
        }

        // 텍스트 리턴
        cv_search.seOnTextListener {
            viewModel.getMovie(it)
        }

        et_search.setText("마블")

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

    override fun initDataBinding() {
        super.initDataBinding()
        viewModel.run {
            moveItems.observe(this@MainActivity, Observer {
                val adapter = rv_movie.adapter as BaseListAdapter<MovieData.Item>
                adapter.submitList(it)
            })
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        cv_search.onPause()
        super.onPause()
    }
}
