package com.tmb.currency.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmb.currency.model.states.LoadingState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    val loadingState = MutableLiveData<LoadingState>().apply {
        LoadingState(false)
    }

    abstract val coroutineExceptionHandler: CoroutineExceptionHandler

    protected fun launchCoroutine(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(coroutineExceptionHandler) {
            block()
        }
    }

    open fun showLoading(vararg labels: String) {
        this.loadingState.value = LoadingState(isLoading = true, labels = labels.toList())
    }

    open fun dismissLoading() {
        this.loadingState.value = LoadingState(isLoading = false)
    }
}