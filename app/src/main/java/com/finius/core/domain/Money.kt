package com.finius.core.domain

import kotlin.math.absoluteValue

data class Money(val cents: Long) {

    fun format(currency: Currency = Currency.BRL): String {
        val isNegative = cents < 0
        val (units, cents) = cents / 100 to cents % 100
        val prefix = if (isNegative) "-" else ""
        return "$prefix${currency.symbol} ${units.integerPartFormat(currency.thousandsSeparator)}${currency.decimalSeparator}${cents.centsPadding()}"
    }

    private fun Long.integerPartFormat(separator: String) = absoluteValue
        .toString()
        .reversed()
        .chunked(3)
        .joinToString(separator = separator)
        .reversed()

    private fun Long.centsPadding() = toString().replace("-", "").padStart(2, padChar = '0')
}

enum class Currency(
    val symbol: String,
    val thousandsSeparator: String,
    val decimalSeparator: String
) {
    BRL("R$", ".", ",")
}

