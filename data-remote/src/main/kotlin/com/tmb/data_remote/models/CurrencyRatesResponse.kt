package com.tmb.data_remote.models

data class CurrencyRatesResponse(
    val updated: Long,
    val base: String,
    val rates: Map<String, Double>
): BaseResponse()