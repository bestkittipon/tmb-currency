package com.tmb.currency.di

import com.tmb.domain.repository.ISampleRepository
import com.tmb.domain.usecases.SampleBaseUseCase
import com.tmb.domain.usecases.SampleUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module


val useCasesModule = module {

    single(named("search")) { provideSampleUseCase(get()) }

}

fun provideSampleUseCase(sampleRepository: ISampleRepository): SampleBaseUseCase {
    return SampleUseCase(sampleRepository)
}
