package com.tmb.currency.di

import com.tmb.data_remote.repository.CurrencyRepository
import com.tmb.domain.repository.ICurrencyRepository
import org.koin.dsl.module


val remoteDataSourceModule = module {

    single<ICurrencyRepository> { CurrencyRepository(apiService = get()) }

}
