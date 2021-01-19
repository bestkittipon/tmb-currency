package com.tmb.domain.model

import java.util.*
import kotlin.collections.ArrayList

data class Currency (
    val currencies: Map<String, String>?,
    val info: ArrayList<CurrencyInfo> = arrayListOf()
): BaseModel()

data class CurrencyInfo(
    val code: String? = null,
    val name: String? = null
)