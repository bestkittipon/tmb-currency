package com.tmb.currency.di

import com.tmb.data_remote.repository.SampleRepository
import com.tmb.domain.repository.ISampleRepository
import org.koin.dsl.module


val remoteDataSourceModule = module {

    single<ISampleRepository> { SampleRepository(apiService = get()) }

}
