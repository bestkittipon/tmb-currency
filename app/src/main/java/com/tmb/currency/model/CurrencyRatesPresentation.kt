package com.tmb.currency.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
data class CurrencyRatesPresentation(
    val updated: Long?,
    val base: String?,
    val rates: Map<String, Double>?,
    val ratesInfo: List<CurrencyRatesInfoPresentation> = arrayListOf()
): BaseModelPresentation(), Parcelable


@Parcelize
data class CurrencyRatesInfoPresentation(
    val code: String? = null,
    val value: Double? = null,
    var name: String? = null
): Parcelable {
    fun getImageUrl() =
        "https://currency.morgrowe.com/images/flag-icons-256/${code?.toLowerCase(
            Locale.ENGLISH
        )}.png"

    fun getTitle(baseCurrencyCode: String) = "1 $baseCurrencyCode = $value $code"
}
