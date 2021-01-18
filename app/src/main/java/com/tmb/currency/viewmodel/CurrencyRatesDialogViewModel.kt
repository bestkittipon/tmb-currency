package com.tmb.currency.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tmb.currency.R
import com.tmb.currency.adapter.CurrencyRatesAdapter
import com.tmb.currency.model.CurrencyInfoPresentation
import com.tmb.currency.model.CurrencyRatesInfoPresentation
import com.tmb.currency.model.CurrencyRatesPresentation
import kotlinx.coroutines.CoroutineExceptionHandler

class CurrencyRatesDialogViewModel: BaseViewModel() {

    val adapter = CurrencyRatesAdapter(R.layout.item_small_content, this)

    val selectedCurrency: LiveData<CurrencyInfoPresentation>
        get() = _selectedCurrency
    private val _selectedCurrency = MutableLiveData<CurrencyInfoPresentation>()

    val selectedCurrencyRate: LiveData<CurrencyRatesInfoPresentation>
        get() = _selectedCurrencyRate
    private val _selectedCurrencyRate = MutableLiveData<CurrencyRatesInfoPresentation>()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        onResultError(exception.message)
    }

    fun init(currencyRatesPresentation: CurrencyRatesPresentation, currencyInfoPresentation: CurrencyInfoPresentation) {
        _selectedCurrency.value = currencyInfoPresentation
        adapter.setData(currencyRatesPresentation.ratesInfo)
        adapter.notifyDataSetChanged()
    }

    fun onSelectCurrencyRate(currencyRatesInfo: CurrencyRatesInfoPresentation) {
        _selectedCurrencyRate.value = currencyRatesInfo
    }

    private fun onResultError(message: String?) {

    }
}