package com.tmb.currency.viewmodels

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.tmb.currency.BaseViewModelTest
import com.tmb.currency.fakes.FakeCurrencyRatesUseCase
import com.tmb.currency.fakes.FakeCurrencyUseCase
import com.tmb.currency.mappers.toPresentation
import com.tmb.currency.utils.Data
import com.tmb.currency.utils.UiState
import com.tmb.currency.utils.observeOnce
import com.tmb.currency.viewmodel.ConvertCurrencyViewModel
import com.tmb.currency.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
internal class ConvertCurrencyViewModelTest: BaseViewModelTest() {

    private lateinit var convertViewModel: ConvertCurrencyViewModel

    @Test
    fun given_a_currency_rates_then_get_result() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.SUCCESS)

            convertViewModel.init(Data.currency.toPresentation(), Data.currency.info[0].toPresentation())

            convertViewModel.currencyRateList.observeOnce { currencyRate ->
                Truth.assertThat(currencyRate.valid).isTrue()
                Truth.assertThat(currencyRate.error?.code).isNull()
                Truth.assertThat(currencyRate.error?.message).isNull()
                Truth.assertThat(currencyRate.rates).isNotEmpty()
                Truth.assertThat(currencyRate.ratesInfo).isNotEmpty()
            }
        }
    }

    @Test
    fun given_an_invalid_state_then_get_view_state_error() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.ERROR)

            convertViewModel.init(Data.currency.toPresentation(), Data.currency.info[0].toPresentation())

            convertViewModel.error.observeOnce { error ->
                Truth.assertThat(error).isNotEmpty()
            }
        }
    }
    
    override fun prepareViewModel(uiState: UiState) {
        val getCurrencyRatesListUseCase = FakeCurrencyRatesUseCase(uiState)
        convertViewModel = ConvertCurrencyViewModel(getCurrencyRatesListUseCase)
    }
}