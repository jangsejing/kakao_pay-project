package com.jess.kakaopay.di.annotaion

import com.jess.kakaopay.common.base.BaseDataSource
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
annotation class DataSourceKey(val value: KClass<out BaseDataSource>)
