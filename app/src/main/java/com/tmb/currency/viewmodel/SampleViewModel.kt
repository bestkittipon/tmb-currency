package com.tmb.currency.viewmodel

import androidx.lifecycle.MutableLiveData
import com.tmb.domain.usecases.CurrencyBaseUseCase
import com.tmb.currency.commons.ExceptionHandler
import com.tmb.currency.model.CharacterPresentation
import com.tmb.currency.model.states.Error
import com.tmb.currency.model.states.SampleViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

internal class SampleViewModel(
    private val currencyUseCase: CurrencyBaseUseCase
) : BaseViewModel() {

    private var searchJob: Job? = null

    var searchViewState = MutableLiveData<SampleViewState>()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        onSearchError(message)
    }

    init {
        searchViewState.value =
            SampleViewState(
                error = null,
                searchResults = null
            )
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }

    fun executeCharacterSearch(characterName: String) {
        //EspressoIdlingResource.increment()
        searchJob?.cancel()
        searchJob = launchCoroutine {
            onSearchLoading()
            delay(500)
            currencyUseCase("1mvT6y5RGXQVH03JVPqA0Br1s2zX6fGGeIeS").collect { results ->
                //val characters = results.map { character -> character.toPresentation() }
                //onSearchComplete(characters)
            }
        }
    }

    private fun onSearchComplete(characters: List<CharacterPresentation>) {
        //EspressoIdlingResource.increment()
        searchViewState.value =
            searchViewState.value?.copy(searchResults = characters)
        dismissLoading()
    }

    private fun onSearchLoading() {
        //EspressoIdlingResource.increment()
        showLoading()
    }

    private fun onSearchError(message: Int) {
        //EspressoIdlingResource.increment()
        searchViewState.value =
            searchViewState.value?.copy(error = Error(message))
        dismissLoading()
    }
}



