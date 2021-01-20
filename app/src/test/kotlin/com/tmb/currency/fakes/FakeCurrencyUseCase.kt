package com.tmb.currency.fakes

import com.tmb.currency.utils.Data
import com.tmb.currency.utils.UiState
import com.tmb.domain.model.Currency
import com.tmb.domain.usecases.CurrencyBaseUseCase

class FakeCurrencyUseCase(
    uiState: UiState
): BaseTestUseCase<Currency, String>(uiState), CurrencyBaseUseCase {
    override fun getValue(params: String): Currency {
        return Data.currency
    }

    override suspend fun invoke(params: String) = execute(params)

}