package com.tmb.currency.fakes

import com.tmb.currency.utils.Data
import com.tmb.currency.utils.UiState
import com.tmb.domain.model.CurrencyRates
import com.tmb.domain.model.CurrencyRatesRequest
import com.tmb.domain.usecases.CurrencyRatesBaseUseCase

class FakeCurrencyRatesUseCase(
    uiState: UiState
): BaseTestUseCase<CurrencyRates, CurrencyRatesRequest>(uiState), CurrencyRatesBaseUseCase {
    override fun getValue(params: CurrencyRatesRequest): CurrencyRates {
        return Data.currencyRates
    }

    override suspend fun invoke(params: CurrencyRatesRequest) = execute(params)

}