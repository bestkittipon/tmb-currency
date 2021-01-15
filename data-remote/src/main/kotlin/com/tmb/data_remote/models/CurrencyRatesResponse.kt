package com.tmb.data_remote.models

data class CurrencyRatesResponse(
    val rates: Map<String, Double>
): BaseResponse()