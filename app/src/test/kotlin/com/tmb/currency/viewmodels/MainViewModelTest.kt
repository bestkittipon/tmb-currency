package com.tmb.currency.viewmodels

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.tmb.currency.BaseViewModelTest
import com.tmb.currency.fakes.FakeCurrencyUseCase
import com.tmb.currency.utils.UiState
import com.tmb.currency.utils.observeOnce
import com.tmb.currency.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
internal class MainViewModelTest: BaseViewModelTest() {

    private lateinit var mainViewModel: MainViewModel

    @Test
    fun given_a_currency_list_then_get_result() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.SUCCESS)

            mainViewModel.getCurrency()

            mainViewModel.currencyResult.observeOnce { currency ->
                Truth.assertThat(currency.valid).isTrue()
                Truth.assertThat(currency.error?.code).isNull()
                Truth.assertThat(currency.error?.message).isNull()
                Truth.assertThat(currency.currencies).isNotEmpty()
                Truth.assertThat(currency.info).isNotEmpty()
            }
        }
    }

    @Test
    fun given_an_invalid_state_then_get_view_state_error() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.ERROR)

            mainViewModel.getCurrency()

            mainViewModel.error.observeOnce { error ->
                Truth.assertThat(error).isNotEmpty()
            }
        }
    }
    
    override fun prepareViewModel(uiState: UiState) {
        val getCurrencyListUseCase = FakeCurrencyUseCase(uiState)
        mainViewModel = MainViewModel(getCurrencyListUseCase)
    }
}