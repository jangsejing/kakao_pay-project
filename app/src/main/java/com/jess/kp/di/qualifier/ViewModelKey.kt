package com.jess.kp.di.qualifier

import androidx.lifecycle.ViewModel
import com.jess.kp.presentation.main.viewmodel.MainViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * @author jess
 * @since 2020.06.12
 */
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
