package com.tmb.currency.mappers

import com.tmb.currency.model.CurrencyInfoPresentation
import com.tmb.currency.model.CurrencyPresentation
import com.tmb.currency.model.CurrencyRatesInfoPresentation
import com.tmb.currency.model.CurrencyRatesPresentation
import com.tmb.currency.model.Error
import com.tmb.domain.model.Currency
import com.tmb.domain.model.CurrencyInfo
import com.tmb.domain.model.CurrencyRates
import com.tmb.domain.model.CurrencyRatesInfo

internal fun CurrencyInfo.toPresentation(): CurrencyInfoPresentation {
    return CurrencyInfoPresentation(this.code, this.name)
}

internal fun Currency.toPresentation(): CurrencyPresentation {
    return CurrencyPresentation(
        this.currencies,
        this.info.map { it.toPresentation() }
    ).apply {
        valid = this@toPresentation.valid
        error = Error()
        error?.code = this@toPresentation.error?.code
        error?.message = this@toPresentation.error?.message
    }
}

internal fun CurrencyRatesInfo.toPresentation(): CurrencyRatesInfoPresentation {
    return CurrencyRatesInfoPresentation(this.code, this.value, this.name)
}

internal fun CurrencyRates.toPresentation(): CurrencyRatesPresentation {
    return CurrencyRatesPresentation(
        this.updated,
        this.base,
        this.rates,
        this.ratesInfo.map { it.toPresentation() }
    ).apply {
        valid = this@toPresentation.valid
        error = Error()
        error?.code = this@toPresentation.error?.code
        error?.message = this@toPresentation.error?.message
    }
}
