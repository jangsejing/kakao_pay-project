package com.jess.kakaopay.presentation.main

import androidx.lifecycle.ViewModel
import com.jess.kakaopay.di.annotaion.ActivityScoped
import com.jess.kakaopay.di.annotaion.ViewModelKey
import com.jess.kakaopay.presentation.main.MainActivity
import com.jess.kakaopay.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * @author jess
 * @since 2020.06.12
 */
@Module
abstract class MainModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

}