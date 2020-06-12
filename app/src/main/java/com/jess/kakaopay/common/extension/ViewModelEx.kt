package com.jess.kakaopay.common.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

/**
 * @author jess
 * @since 2020.06.12
 */
fun <VM : ViewModel> AppCompatActivity.createActivityViewModel(
    factory: ViewModelProvider.Factory,
    classType: Class<VM>
): VM = ViewModelProvider(this, factory)[classType]

/**
 * @author jess
 * @since 2020.06.12
 */
inline fun <VM : ViewModel> Fragment.createFragmentViewModel(
    viewModelClass: KClass<VM>,
    crossinline body: () -> ViewModelProvider.Factory
): Lazy<VM> {
    return createViewModelLazy(viewModelClass, { viewModelStore }) { body() }
}