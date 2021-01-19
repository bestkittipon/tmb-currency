package com.tmb.currency.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
data class CurrencyPresentation (
    val currencies: Map<String, String>?,
    val info: List<CurrencyInfoPresentation> = arrayListOf()
): BaseModelPresentation(), Parcelable

@Parcelize
data class CurrencyInfoPresentation(
    val code: String? = null,
    val name: String? = null
): Parcelable {
    fun getImageUrl() = "https://currency.morgrowe.com/images/flag-icons-256/${code?.toLowerCase(Locale.ENGLISH)}.png"
}