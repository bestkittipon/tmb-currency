package com.tmb.currency.ui

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.tmb.currency.R
import com.tmb.currency.base.DataBindingActivity
import com.tmb.currency.databinding.ActivityMainBinding
import com.tmb.currency.dialog.MessageDialog
import com.tmb.currency.mappers.toPresentation
import com.tmb.currency.viewmodel.MainViewModel

internal class MainActivity : DataBindingActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class) {

    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = this.window
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,
            R.color.colorPrimaryDark
        )
    }

    override fun initialView() {
        binding.viewModel = viewModel
        viewModel.getCurrency()

        binding.etSearchCurrency.addTextChangedListener {
            viewModel.onSearchCurrency(it.toString())
        }

        viewModel.selectedCurrency.observe(this, Observer { currency ->
            currency.toPresentation().let {currencyInfo ->
                viewModel.currencyResult.value?.toPresentation()?.let { currencyPresentation ->
                    startActivity(ConvertCurrencyActivity.newIntent(this, currencyPresentation, currencyInfo))
                }
            }
        })

        viewModel.error.observe(this, Observer {
            if (it.isNotEmpty()) {
                MessageDialog.newDialog("Error Request", it)
                    .show(supportFragmentManager, ConvertCurrencyActivity::javaClass.toString())
            }
        })
    }
}
