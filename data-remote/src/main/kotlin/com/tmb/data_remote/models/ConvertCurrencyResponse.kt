package com.tmb.data_remote.models

data class ConvertCurrencyResponse(
    val updated: Int,
    val conversion: Conversion
): BaseResponse()

data class Conversion(
    val amount: Double,
    val from: String,
    val to: String,
    val result: Double
)