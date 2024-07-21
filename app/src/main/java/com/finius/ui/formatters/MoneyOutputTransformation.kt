package com.finius.ui.formatters

import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.runtime.Stable
import com.finius.core.domain.Money

@Stable
class MoneyOutputTransformation: OutputTransformation {
    override fun TextFieldBuffer.transformOutput() {
        val parsedLong = originalText.toString().toLongOrNull() ?: 0L
        val parsedMoney = Money(parsedLong).format()
        this.replace(0, originalText.length, parsedMoney)
    }
}