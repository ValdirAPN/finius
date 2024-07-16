package com.finius.ui.strings

import cafe.adriel.lyricist.LyricistStrings
import com.finius.features.account.bankAccounts.presentation.BankAccountStrings
import com.finius.features.account.bankAccounts.presentation.BankAccountStringsPt
import com.finius.features.account.creditCards.presentation.CreditCardStrings
import com.finius.features.account.creditCards.presentation.CreditCardStringsPt
import com.finius.features.categories.presentation.CategoriesStrings
import com.finius.features.categories.presentation.CategoriesStringsPt
import com.finius.features.home.presentation.HomeStrings
import com.finius.features.home.presentation.HomeStringsPt
import com.finius.features.transaction.presentation.TransactionStrings
import com.finius.features.transaction.presentation.TransactionStringsPt

data class Strings(
    val homeStrings: HomeStrings,
    val transactionStrings: TransactionStrings,
    val bankAccountStrings: BankAccountStrings,
    val creditCardStrings: CreditCardStrings,
    val categoriesStrings: CategoriesStrings,
)

@LyricistStrings(languageTag = Locales.PT, default = true)
val StringsPt = Strings(
    homeStrings = HomeStringsPt,
    transactionStrings = TransactionStringsPt,
    bankAccountStrings = BankAccountStringsPt,
    creditCardStrings = CreditCardStringsPt,
    categoriesStrings = CategoriesStringsPt
)
