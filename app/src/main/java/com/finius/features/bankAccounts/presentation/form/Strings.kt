package com.finius.features.bankAccounts.presentation.form

import com.finius.features.bankAccounts.presentation.form.balance.BankAccountBalanceStrings
import com.finius.features.bankAccounts.presentation.form.balance.BankAccountBalanceStringsPt
import com.finius.features.bankAccounts.presentation.form.brand.BankAccountBrandStrings
import com.finius.features.bankAccounts.presentation.form.brand.BankAccountBrandStringsPt
import com.finius.features.bankAccounts.presentation.form.name.BankAccountNameStrings
import com.finius.features.bankAccounts.presentation.form.name.BankAccountNameStringsPt

data class AccountFormStrings(
    val bankAccountBalanceStrings: BankAccountBalanceStrings,
    val bankAccountNameStrings: BankAccountNameStrings,
    val bankAccountBrandStrings: BankAccountBrandStrings,
)

val AccountFormStringsPt = AccountFormStrings(
    bankAccountBalanceStrings = BankAccountBalanceStringsPt,
    bankAccountNameStrings = BankAccountNameStringsPt,
    bankAccountBrandStrings = BankAccountBrandStringsPt
)