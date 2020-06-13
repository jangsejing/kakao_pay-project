package com.jess.kakaopay.di

import com.jess.kakaopay.JessApplication
import com.jess.kakaopay.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author jess
 * @since 2020.06.12
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        DataSourceModule::class,
        DispatcherModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent : AndroidInjector<JessApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: JessApplication): AppComponent
    }
}