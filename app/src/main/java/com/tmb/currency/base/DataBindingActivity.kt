package com.tmb.currency.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.tmb.currency.BR
import com.tmb.currency.viewmodel.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

internal abstract class DataBindingActivity<Binding : ViewDataBinding, out VM : BaseViewModel>(classViewModel: KClass<VM>) :
    BaseActivity() {

    protected lateinit var binding: Binding

    protected val viewModel: VM by viewModel(classViewModel)

    protected abstract fun getLayoutResId(): Int

    protected abstract fun initialView()

    protected abstract fun observeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResId())
        binding.lifecycleOwner = this
        subscribeToViewModel(viewModel)
        initialView()
    }

    override fun onResume() {
        super.onResume()
        observeViewModel()
    }

}
