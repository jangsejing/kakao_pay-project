package com.jess.kakaopay.di.module

import com.jess.kakaopay.di.annotaion.ActivityScoped
import com.jess.kakaopay.presentation.detail.DetailActivity
import com.jess.kakaopay.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author jess
 * @since 2020.06.12
 */
@Module
abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindDetailActivity(): DetailActivity

}