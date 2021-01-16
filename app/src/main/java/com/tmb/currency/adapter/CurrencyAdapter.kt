package com.tmb.currency.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tmb.currency.BR
import com.tmb.currency.R
import com.tmb.currency.base.BaseAdapter
import com.tmb.currency.base.BaseViewHolder
import com.tmb.currency.databinding.ItemMediumContentBinding
import com.tmb.currency.viewmodel.MainViewModel
import com.tmb.domain.model.CurrencyInfo


class CurrencyAdapter(layoutId: Int, viewModel: MainViewModel): BaseAdapter<MainViewModel, ItemMediumContentBinding, CurrencyInfo, CurrencyViewHolder>(layoutId, viewModel) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, parent, false)
        return CurrencyViewHolder(binding as ItemMediumContentBinding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        getData().getOrNull(position)?.let {
            holder.bindView(it, viewModel, position)
        }
    }

    /*fun onRemoveDevice(position: Int, viewModel: CurrencyViewHolder) {
        (getData() as? ArrayList<Device>)?.apply {
            removeAt(position)
        }?.also {
            setData(it.toList())
            updateViewModel(viewModel)
            notifyItemRemoved(position)
            notifyItemRangeChanged(0, itemCount)
        }
    }*/
}

class CurrencyViewHolder(private val viewBinding: ItemMediumContentBinding): BaseViewHolder<ItemMediumContentBinding, CurrencyInfo>(viewBinding) {
    override fun bindView(data: CurrencyInfo, viewModel: ViewModel, index: Int) {
        viewBinding.setVariable(BR.obj, data)
        viewBinding.setVariable(BR.position, index)
        viewBinding.setVariable(BR.viewModel, viewModel)
        viewBinding.executePendingBindings()

        val requestOptions = RequestOptions().apply {
            this.placeholder(R.drawable.info_icon)
            this.error(R.drawable.info_icon)
        }

        Glide.with(viewBinding.root)
            .setDefaultRequestOptions(requestOptions)
            .load(data.getImageUrl())
            .into(viewBinding.imgLogo)
    }
}