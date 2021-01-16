package com.tmb.currency.di

import com.tmb.domain.repository.ICurrencyRepository
import com.tmb.domain.usecases.CurrencyBaseUseCase
import com.tmb.domain.usecases.CurrencyRatesBaseUseCase
import com.tmb.domain.usecases.CurrencyRatesUseCase
import com.tmb.domain.usecases.CurrencyUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module


val useCasesModule = module {

    single(named("get_currency")) { provideCurrencyUseCase(get()) }

}

fun provideCurrencyUseCase(currencyRepository: ICurrencyRepository): CurrencyBaseUseCase {
    return CurrencyUseCase(currencyRepository)
}

fun provideCurrencyRatesUseCase(currencyRepository: ICurrencyRepository): CurrencyRatesBaseUseCase {
    return CurrencyRatesUseCase(currencyRepository)
}
