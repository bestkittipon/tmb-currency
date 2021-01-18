package com.tmb.domain.model

import java.util.*
import kotlin.collections.ArrayList

data class CurrencyRates (
    val updated: Long,
    val base: String,
    val rates: Map<String, Double>,
    val ratesInfo: ArrayList<CurrencyRatesInfo> = arrayListOf()
): BaseModel()

data class CurrencyRatesInfo(
    val code: String? = null,
    val value: Double? = null,
    var name: String? = null
) {
    fun getImageUrl() = "https://currency.morgrowe.com/images/flag-icons-256/${code?.toLowerCase(Locale.ENGLISH)}.png"

    fun getTitle(baseCurrencyCode: String) = "1 $baseCurrencyCode = $value $code"
}