package com.finius.features.account.bankAccounts.presentation

import com.finius.features.account.bankAccounts.presentation.form.BankAccountFormStrings
import com.finius.features.account.bankAccounts.presentation.form.BankAccountFormStringsPt
import com.finius.features.account.bankAccounts.presentation.home.BankAccountsHomeStrings
import com.finius.features.account.bankAccounts.presentation.home.BankAccountsHomeStringsPt

data class BankAccountStrings(
    val bankAccountsHomeStrings: BankAccountsHomeStrings,
    val bankAccountFormStrings: BankAccountFormStrings,
)

val BankAccountStringsPt = BankAccountStrings(
    bankAccountsHomeStrings = BankAccountsHomeStringsPt,
    bankAccountFormStrings = BankAccountFormStringsPt
)