package com.jess.kakaopay.di.module

import androidx.lifecycle.ViewModel
import com.jess.kakaopay.di.qualifier.ViewModelKey
import com.jess.kakaopay.presentation.main.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author jess
 * @since 2020.06.12
 */
@Module(includes = [ViewModelFactoryModule::class])
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(DetailViewModel::class)
//    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

}