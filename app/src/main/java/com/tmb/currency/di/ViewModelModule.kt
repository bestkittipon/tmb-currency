package com.tmb.currency.di

import com.tmb.currency.viewmodel.ConvertCurrencyViewModel
import com.tmb.currency.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        MainViewModel(
            currencyUseCase = get(named("get_currency"))
        )
    }

    viewModel {
        ConvertCurrencyViewModel(
            currencyRatesUseCase = get(named("get_rate_currency"))
        )
    }
}
