package com.tmb.currency.utils

import com.tmb.domain.model.*


object Data {
    val currency = Currency(
        currencies = mapOf(Pair("USD", "United States Dollar")),
        info = arrayListOf(CurrencyInfo("USD", "United States Dollar"))
    ).apply {
        this.valid = true
        this.error = Error()
    }

    val currencyRates = CurrencyRates(
        updated = 1611123846,
        base = "USD",
        rates = mapOf(Pair("USDHUF", 294.644999)),
        ratesInfo = arrayListOf(CurrencyRatesInfo("HUF", 294.644999))
    ).apply {
        this.valid = true
        this.error = Error()
    }
}