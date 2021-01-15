package com.tmb.currency.di

import com.tmb.domain.repository.ICurrencyRepository
import com.tmb.domain.usecases.CurrencyBaseUseCase
import com.tmb.domain.usecases.CurrencyUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module


val useCasesModule = module {

    single(named("key")) { provideSampleUseCase(get()) }

}

fun provideSampleUseCase(currencyRepository: ICurrencyRepository): CurrencyBaseUseCase {
    return CurrencyUseCase(currencyRepository)
}
