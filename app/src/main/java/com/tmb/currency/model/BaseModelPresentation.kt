package com.tmb.currency.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class BaseModelPresentation: Parcelable {
    var valid: Boolean = false
    var error: Error? = null
}

@Parcelize
class Error: Parcelable {
    var code: Int? = null
    var message: String? = null
}