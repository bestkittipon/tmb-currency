package com.tmb.data_remote.models

data class CurrencyRatesResponse(
    val timestamp: Long,
    val source: String,
    val quotes: Map<String, Double>
): BaseResponse()