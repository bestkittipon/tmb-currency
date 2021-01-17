package com.tmb.domain.repository

import com.tmb.domain.model.Conversion
import com.tmb.domain.model.Currency
import com.tmb.domain.model.CurrencyRates
import kotlinx.coroutines.flow.Flow

interface ICurrencyRepository {
    suspend fun getCurrency(key: String): Flow<Currency>

    suspend fun getRatesCurrency(key: String, currencyKey: String): Flow<CurrencyRates>

    suspend fun convertCurrency(key: String, amount: Double, fromCurrencyKey: String, toCurrencyKey: String): Flow<Conversion>
}