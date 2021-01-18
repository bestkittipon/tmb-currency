package com.tmb.currency.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tmb.currency.model.CurrencyInfoPresentation
import com.tmb.currency.model.CurrencyPresentation
import com.tmb.domain.model.CurrencyRates
import com.tmb.domain.model.CurrencyRatesInfo
import com.tmb.domain.model.CurrencyRatesRequest
import com.tmb.domain.usecases.CurrencyRatesBaseUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect

class ConvertCurrencyViewModel(
    private val currencyRatesUseCase: CurrencyRatesBaseUseCase
) : BaseViewModel() {

    private var searchJob: Job? = null

    val currencyList: LiveData<CurrencyPresentation>
        get() = _currencyList
    private val _currencyList = MutableLiveData<CurrencyPresentation>()

    val selectedCurrency: LiveData<CurrencyInfoPresentation>
        get() = _selectedCurrency
    private val _selectedCurrency = MutableLiveData<CurrencyInfoPresentation>()

    val selectedCurrencyRates: LiveData<CurrencyRatesInfo>
        get() = _selectedCurrencyRates
    private val _selectedCurrencyRates = MutableLiveData<CurrencyRatesInfo>()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        onResultError(exception.message)
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }

    fun init(currencyList: CurrencyPresentation, selectedCurrencyInfo: CurrencyInfoPresentation) {
        _currencyList.value = currencyList
        _selectedCurrency.value = selectedCurrencyInfo
        searchJob = launchCoroutine {
            onResultLoading()
            currencyRatesUseCase(CurrencyRatesRequest("22b335212e7c832f7aa8d843bf836094", selectedCurrencyInfo.code ?: "")).collect { results ->
                onResultComplete(results)
            }
        }
    }

    private fun onResultComplete(currencyRate: CurrencyRates) {
        _currencyList.value?.currencies?.let { currencyMap ->
            currencyRate.ratesInfo.map { it.name = currencyMap[it.code] }
        }

        _selectedCurrencyRates.value = currencyRate.ratesInfo[12]
        dismissLoading()
    }

    private fun onResultLoading() {
        //EspressoIdlingResource.increment()
        showLoading()
    }

    private fun onResultError(message: String?) {
        //EspressoIdlingResource.increment()
        dismissLoading()
    }
}



