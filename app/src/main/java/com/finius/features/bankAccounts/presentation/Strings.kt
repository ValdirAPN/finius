package com.finius.features.bankAccounts.presentation

import com.finius.features.bankAccounts.presentation.form.AccountFormStrings
import com.finius.features.bankAccounts.presentation.form.AccountFormStringsPt
import com.finius.features.bankAccounts.presentation.home.AccountHomeStrings
import com.finius.features.bankAccounts.presentation.home.AccountHomeStringsPt

data class AccountsStrings(
    val accountHomeStrings: AccountHomeStrings,
    val accountFormStrings: AccountFormStrings,
)

val AccountsStringsPt = AccountsStrings(
    accountHomeStrings = AccountHomeStringsPt,
    accountFormStrings = AccountFormStringsPt
)