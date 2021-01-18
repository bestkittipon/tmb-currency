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
import com.tmb.currency.databinding.ItemSmallContentBinding
import com.tmb.currency.model.CurrencyRatesInfoPresentation
import com.tmb.currency.viewmodel.CurrencyRatesDialogViewModel

class CurrencyRatesAdapter(layoutId: Int, viewModel: CurrencyRatesDialogViewModel): BaseAdapter<CurrencyRatesDialogViewModel, ItemSmallContentBinding, CurrencyRatesInfoPresentation, CurrencyRatesViewHolder>(layoutId, viewModel) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRatesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, parent, false)
        return CurrencyRatesViewHolder(binding as ItemSmallContentBinding)
    }

    override fun onBindViewHolder(holder: CurrencyRatesViewHolder, position: Int) {
        getData().getOrNull(position)?.let {
            holder.bindView(it, viewModel, position)
        }
    }
}

class CurrencyRatesViewHolder(private val viewBinding: ItemSmallContentBinding): BaseViewHolder<ItemSmallContentBinding, CurrencyRatesInfoPresentation>(viewBinding) {
    override fun bindView(data: CurrencyRatesInfoPresentation, viewModel: ViewModel, index: Int) {
        viewBinding.setVariable(BR.obj, data)
        viewBinding.setVariable(BR.viewModel, viewModel)
        viewBinding.executePendingBindings()

        val requestOptions = RequestOptions().apply {
            this.placeholder(R.drawable.info_icon)
            this.error(R.drawable.info_icon)
        }

        Glide.with(viewBinding.root)
            .setDefaultRequestOptions(requestOptions)
            .load(data.getImageUrl())
            .into(viewBinding.imgLogoToCurrency)
    }
}