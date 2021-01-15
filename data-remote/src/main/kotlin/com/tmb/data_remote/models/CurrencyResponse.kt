package com.tmb.data_remote.models

data class CurrencyResponse(
    val currencies: Map<String, String>
): BaseResponse()