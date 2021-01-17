package com.tmb.currency.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tmb.currency.R
import com.tmb.currency.base.DataBindingActivity
import com.tmb.currency.databinding.ActivityConvertCurrencyBinding
import com.tmb.currency.databinding.ActivityMainBinding
import com.tmb.currency.model.CurrencyInfoPresentation
import com.tmb.currency.model.CurrencyPresentation
import com.tmb.currency.viewmodel.ConvertCurrencyViewModel
import com.tmb.currency.viewmodel.MainViewModel


internal class ConvertCurrencyActivity : DataBindingActivity<ActivityConvertCurrencyBinding, ConvertCurrencyViewModel>(ConvertCurrencyViewModel::class) {

    override fun getLayoutResId() = R.layout.activity_convert_currency

    private val currencyList by lazy { intent.extras?.getParcelable<CurrencyPresentation>("currencyList") }
    private val selectedCurrencyInfo by lazy { intent.extras?.getParcelable<CurrencyInfoPresentation>("selectedCurrencyInfo") }

    companion object {
        fun newIntent(context: Context, currency: CurrencyPresentation, selectedCurrencyInfo: CurrencyInfoPresentation): Intent {
            return Intent(context, ConvertCurrencyActivity::class.java).also {
                it.putExtra("currencyList", currency)
                it.putExtra("selectedCurrencyInfo", selectedCurrencyInfo)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,
            R.color.colorPrimaryDark
        )
    }

    override fun initialView() {
        binding.viewModel = viewModel
        binding.obj = this

        if (selectedCurrencyInfo != null && currencyList != null) {
            viewModel.init(currencyList!!, selectedCurrencyInfo!!)

            val requestOptions = RequestOptions().apply {
                this.placeholder(R.drawable.info_icon)
                this.error(R.drawable.info_icon)
            }

            Glide.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(selectedCurrencyInfo!!.getImageUrl())
                .into(binding.imgLogo)
        }

    }

    override fun observeViewModel() {

    }

    fun onCloseClicked(view: View) {
        this.finish()
    }
}
