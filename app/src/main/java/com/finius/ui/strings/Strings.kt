package com.finius.ui.strings

import cafe.adriel.lyricist.LyricistStrings
import com.finius.features.home.presentation.HomeStrings
import com.finius.features.home.presentation.HomeStringsPt
import com.finius.features.transaction.presentation.TransactionStrings
import com.finius.features.transaction.presentation.TransactionStringsPt

data class Strings(
    val homeStrings: HomeStrings,
    val transactionStrings: TransactionStrings
)

@LyricistStrings(languageTag = Locales.PT, default = true)
val StringsPt = Strings(
    homeStrings = HomeStringsPt,
    transactionStrings = TransactionStringsPt
)
