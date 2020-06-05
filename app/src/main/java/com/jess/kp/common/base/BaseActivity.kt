package com.jess.kp.common.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.jess.kp.common.extension.createViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * @author jess
 * @since 2020.06.12
 */
abstract class BaseActivity<VD : ViewDataBinding, VM : BaseViewModel> : DaggerAppCompatActivity() {

    // ViewDataBinding
    lateinit var binding: VD

    // 레이아웃 ID
    protected abstract val layoutRes: Int

    // ViewModel Class
    protected abstract val viewModelClass: Class<VM>

    // ViewModel Factory
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    // AAC ViewModel
    protected val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        createViewModel(viewModelFactory, viewModelClass)
    }

    // 레이아웃 초기화
    abstract fun initLayout()

    // onCreate 완료
    abstract fun onCreated(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initLayout()
        onCreated(savedInstanceState)
    }

    // 데이터 바인딩 초기화
    protected open fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.run {
            lifecycleOwner = this@BaseActivity
//            setVariable(BR.viewModel, viewModel)
        }
    }
}
