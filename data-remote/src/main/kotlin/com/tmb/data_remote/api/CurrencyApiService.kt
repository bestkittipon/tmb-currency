package com.tmb.data_remote.api

import com.tmb.data_remote.models.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CurrencyApiService {

    @GET("currencies/")
    suspend fun getCurrency(@Query("key") key: String): CurrencyResponse

    @GET("rates/")
    suspend fun getCurrencyRates(@Query("key") key: String, @Query("base") currencyKey: String): CurrencyRatesResponse

    @GET("convert/")
    suspend fun convertCurrency(
        @Query("key") key: String,
        @Query("amount") amount: Double,
        @Query("from") fromCurrencyKey: String,
        @Query("to") toCurrencyKey: String
    ): ConvertCurrencyResponse
}