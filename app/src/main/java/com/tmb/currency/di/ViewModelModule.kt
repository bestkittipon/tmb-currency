package com.tmb.currency.di

import com.tmb.currency.viewmodel.SampleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        SampleViewModel(
            sampleUseCase = get(named("search"))
        )
    }
}
