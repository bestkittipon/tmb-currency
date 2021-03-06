package com.tmb.data_remote.meppers

import com.tmb.data_remote.models.ConvertCurrencyResponse
import com.tmb.data_remote.models.CurrencyRatesResponse
import com.tmb.data_remote.models.CurrencyResponse
import com.tmb.domain.model.Conversion
import com.tmb.domain.model.Currency
import com.tmb.domain.model.CurrencyRates
import com.tmb.domain.model.Error

internal fun ConvertCurrencyResponse.toDomain(): Conversion {
    return Conversion(this.conversion.amount, this.conversion.from, this.conversion.to, this.conversion.result)
}

internal fun CurrencyResponse.toDomain(): Currency {
    return Currency(this.currencies).also { currency ->
        currency.valid = this.valid
        currency.error = Error()
        currency.error?.code = this.error?.code
        currency.error?.message = this.error?.message
    }
}

internal fun CurrencyRatesResponse.toDomain(): CurrencyRates {
    return CurrencyRates(this.timestamp, this.source, this.quotes).also { currencyRates ->
        currencyRates.valid = this.valid
        currencyRates.error = Error()
        currencyRates.error?.code = this.error?.code
        currencyRates.error?.message = this.error?.message
    }
}