package com.tmb.data_remote.api

import com.tmb.data_remote.models.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CurrencyApiService {

    @GET("list")
    suspend fun getCurrency(@Query("access_key") key: String): CurrencyResponse

    @GET("live")
    suspend fun getCurrencyRates(@Query("access_key") key: String, @Query("source") currencyKey: String): CurrencyRatesResponse

    @GET("convert")
    suspend fun convertCurrency(
        @Query("key") key: String,
        @Query("amount") amount: Double,
        @Query("from") fromCurrencyKey: String,
        @Query("to") toCurrencyKey: String
    ): ConvertCurrencyResponse
}