package com.tmb.data_remote.repository

import com.tmb.data_remote.api.CurrencyApiService
import com.tmb.data_remote.meppers.toDomain
import com.tmb.domain.model.Conversion
import com.tmb.domain.repository.ICurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrencyRepository(
    private val apiService: CurrencyApiService
) : ICurrencyRepository {
    override suspend fun getCurrency(key: String): Flow<Map<String, String>> = flow {
        val currencyResponse = apiService.getCurrency(key)
        val currencies = mutableMapOf<String, String>()
        currencies.putAll(currencyResponse.currencies)
        emit(currencies)
    }

    override suspend fun getRatesCurrency(key: String, currencyKey: String): Flow<Map<String, Double>>  = flow {
        val currencyRatesResponse = apiService.getCurrencyRates(key, currencyKey)
        val rates = mutableMapOf<String, Double>()
        rates.putAll(currencyRatesResponse.rates)
        emit(rates)
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