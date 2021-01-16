package com.tmb.currency.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : ViewModel, T2 : ViewDataBinding, T3, T4 : BaseViewHolder<T2,T3>> (@LayoutRes val layoutId: Int, var viewModel: T): RecyclerView.Adapter<T4>() {
    private var itemData: List<T3> = listOf()
    var binding: ViewDataBinding? = null

    fun updateViewModel(_viewModel: T) {
        viewModel = _viewModel
    }

    fun setData(data: List<T3>) {
        itemData = data
    }

    fun getData(): List<T3> {
        return itemData
    }

    override fun getItemCount() = itemData.size

}