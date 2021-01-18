package com.tmb.data_remote.repository

import com.tmb.data_remote.api.CurrencyApiService
import com.tmb.data_remote.meppers.toDomain
import com.tmb.domain.model.*
import com.tmb.domain.repository.ICurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrencyRepository(
    private val apiService: CurrencyApiService
) : ICurrencyRepository {
    override suspend fun getCurrency(key: String): Flow<Currency> = flow {
        val currencyResponse = apiService.getCurrency(key)
        val currencyResult = currencyResponse.toDomain()
        currencyResult.currencies.map {
            currencyResult.info.add(CurrencyInfo(it.key, it.value))
        }
        emit(currencyResult)
    }

    override suspend fun getRatesCurrency(key: String, currencyKey: String): Flow<CurrencyRates>  = flow {
        val currencyRatesResponse = apiService.getCurrencyRates(key, currencyKey)
        val currencyRatesResult = currencyRatesResponse.toDomain()
        currencyRatesResult.rates.map {
            val currencyCode = it.key.replace(currencyRatesResult.base, "")
            currencyRatesResult.ratesInfo.add(CurrencyRatesInfo(currencyCode, it.value))
        }
        emit(currencyRatesResult)
    }

    override suspend fun convertCurrency(
        key: String,
        amount: Double,
        fromCurrencyKey: String,
        toCurrencyKey: String
    ): Flow<Conversion> = flow {
        val convertCurrencyResponse = apiService.convertCurrency(key, amount, fromCurrencyKey, toCurrencyKey)
        emit(convertCurrencyResponse.toDomain())
    }

}