package com.jess.kakaopay.presentation.main

import com.jess.kakaopay.common.base.BaseViewModel
import com.jess.kakaopay.repository.datasource.main.MainDataSource
import javax.inject.Inject

/**
 * @author jess
 * @since 2020.06.12
 */
class MainViewModel @Inject constructor(
    private val dataSource: MainDataSource
) : BaseViewModel() {

}
