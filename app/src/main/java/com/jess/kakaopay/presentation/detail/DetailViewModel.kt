package com.jess.kakaopay.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.jess.kakaopay.common.base.BaseViewModel
import com.jess.kakaopay.data.MovieData
import javax.inject.Inject

/**
 * @author jess
 * @since 2020.06.12
 */
class DetailViewModel @Inject constructor(
) : BaseViewModel() {

    private val _item = MutableLiveData<MovieData.Item>()
    val item: LiveData<MovieData.Item> get() = _item

    private val _detailUrl = MutableLiveData<String>()
    val detailUrl: LiveData<String> = _detailUrl.switchMap {
        liveData { emit(it) }
    }

    fun setData(item: MovieData.Item?) {
        item?.let {
            _item.value = item
        }
    }

    fun moveDetailInfo() {
        item.value?.let {
            _detailUrl.value = it.link
        }

    }
}
