package com.tmb.domain.model


data class Conversion(
    val amount: Double,
    val from: String,
    val to: String,
    val result: Double
)
