package com.tmb.data_remote.meppers

import com.tmb.data_remote.models.ConvertCurrencyResponse
import com.tmb.domain.model.Conversion

internal fun ConvertCurrencyResponse.toDomain(): Conversion {
    return Conversion(this.conversion.amount, this.conversion.from, this.conversion.to, this.conversion.result)
}