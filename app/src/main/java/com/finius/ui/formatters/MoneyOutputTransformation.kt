package com.finius.ui.formatters

import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.insert
import java.text.NumberFormat
import java.util.Locale

object MoneyOutputTransformation : OutputTransformation {
    override fun TextFieldBuffer.transformOutput() {
        if (originalText.isNotEmpty()) {
            println("Original Text: $originalText")
            val numberFormat = NumberFormat.getNumberInstance(Locale("pt", "BR"))
            val textToDouble = originalText.toString().toDouble() / 100
            val newValue = numberFormat.format(textToDouble)
            replace(0, originalText.length, newValue)
//            insert(0,"R$ ")
        }
    }
}