package com.tmb.currency

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.tmb.currency.di.networkModule
import com.tmb.currency.di.remoteDataSourceModule
import com.tmb.currency.di.useCasesModule
import com.tmb.currency.di.viewModelsModule

class CurrencyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CurrencyApplication)
            modules(
                networkModule,
                remoteDataSourceModule,
                useCasesModule,
                viewModelsModule
            )
        }
    }
}