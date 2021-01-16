package com.tmb.currency.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.tmb.currency.BR

abstract class BaseViewHolder<T : ViewDataBinding, T2>(private val viewBinding : T) : RecyclerView.ViewHolder(viewBinding.root) {
    open fun bindView(data: T2, viewModel: ViewModel, index: Int) {
        viewBinding.setVariable(BR.obj, data)
        viewBinding.setVariable(BR.viewModel, viewModel)
        viewBinding.executePendingBindings()
    }
}