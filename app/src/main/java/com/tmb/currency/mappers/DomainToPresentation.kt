package com.tmb.currency.mappers

import com.tmb.currency.model.CurrencyInfoPresentation
import com.tmb.currency.model.CurrencyPresentation
import com.tmb.domain.model.Currency
import com.tmb.domain.model.CurrencyInfo

internal fun CurrencyInfo.toPresentation(): CurrencyInfoPresentation {
    return CurrencyInfoPresentation(this.code, this.name)
}

internal fun Currency.toPresentation(): CurrencyPresentation {
    return CurrencyPresentation(
        this.currencies,
        this.info.map { it.toPresentation() }
    )
}
