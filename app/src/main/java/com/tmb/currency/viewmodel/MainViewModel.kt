package com.tmb.currency.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tmb.currency.R
import com.tmb.currency.adapter.CurrencyAdapter
import com.tmb.domain.usecases.CurrencyBaseUseCase
import com.tmb.domain.model.Currency
import com.tmb.domain.model.CurrencyInfo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import java.util.*

class MainViewModel(
    private val currencyUseCase: CurrencyBaseUseCase
) : BaseViewModel() {

    private var searchJob: Job? = null
    val adapter = CurrencyAdapter(R.layout.item_medium_content, this)

    val currencyResult: LiveData<Currency>
        get() = _currencyResult
    private val _currencyResult = MutableLiveData<Currency>()

    val selectedCurrency: LiveData<CurrencyInfo>
        get() = _selectedCurrency
    private val _selectedCurrency = MutableLiveData<CurrencyInfo>()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        onResultError(exception.message)
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }

    fun getCurrency() {
        //EspressoIdlingResource.increment()
        searchJob = launchCoroutine {
            onResultLoading()
            currencyUseCase("hOwgpdhjjp5U3qW9PHPv9N3edlzizkLPorev").collect { results ->
                if (results.valid) {
                    onResultComplete(results)
                } else {
                    onResultError(results.error?.message)
                }
            }
        }
    }

    fun onSearchCurrency(textSearch: String) {
        searchJob?.cancel()
        searchJob = launchCoroutine {
            delay(500)
            _currencyResult.value?.info?.filter { it.code?.toLowerCase(Locale.ENGLISH)?.contains(textSearch.toLowerCase(Locale.ENGLISH)) == true }?.also {
                adapter.setData(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

    fun onSelectCurrency(currencyInfo: CurrencyInfo) {
        _selectedCurrency.postValue(currencyInfo)
    }

    private fun onResultComplete(currency: Currency) {
        //EspressoIdlingResource.increment()
        _currencyResult.postValue(currency)
        adapter.setData(currency.info)
        adapter.notifyDataSetChanged()
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



