package br.com.finius.domain.model

import android.icu.text.NumberFormat
import java.util.Locale

data class Money(val cents: Long) {

    fun format(locale: Locale = Locale.getDefault()): String {
        val numberFormat = NumberFormat.getCurrencyInstance(locale)
        return numberFormat.format(cents / 100.0)
    }
}