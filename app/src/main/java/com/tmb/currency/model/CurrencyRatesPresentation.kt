package com.tmb.currency.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.tmb.currency.BuildConfig
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*


@Parcelize
data class CurrencyRatesPresentation(
    val updated: Long?,
    val base: String?,
    val rates: Map<String, Double>?,
    val ratesInfo: List<CurrencyRatesInfoPresentation> = arrayListOf()
): BaseModelPresentation(), Parcelable {

    @SuppressLint("SimpleDateFormat")
    fun getAsOfDateTime() = try {
        updated?.let {
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            val netDate = Date(updated * 1000)
            sdf.format(netDate)
        } ?: ""

    } catch (e: Exception) {
        ""
    }
}


@Parcelize
data class CurrencyRatesInfoPresentation(
    val code: String? = null,
    val value: Double? = null,
    var name: String? = null
): Parcelable {
    fun getImageUrl() = BuildConfig.IMAGE_PATH.replace("{code}", code?.toLowerCase(Locale.ENGLISH) ?: "")

    fun getTitle(baseCurrencyCode: String) = "1 $baseCurrencyCode = $value $code"
}
