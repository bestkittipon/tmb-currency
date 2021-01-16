package com.tmb.currency.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tmb.currency.R
import com.tmb.currency.adapter.CurrencyAdapter
import com.tmb.domain.usecases.CurrencyBaseUseCase
import com.tmb.domain.model.Currency
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

class MainViewModel(
    private val currencyUseCase: CurrencyBaseUseCase
) : BaseViewModel() {

    private var searchJob: Job? = null
    val adapter = CurrencyAdapter(R.layout.item_medium_content, this)

    val currencyResult: LiveData<Currency>
        get() = _currencyResult
    private val _currencyResult = MutableLiveData<Currency>()

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
            delay(500)
            currencyUseCase("6hsEkhgYOfei6ORT8IEYX7LkHpAxrQzxcG1U").collect { results ->
                Log.d("best_test", results.info.toString())
                if (results.valid) {
                    onResultComplete(results)
                } else {
                    onResultError(results.error?.message)
                }
            }
        }
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



