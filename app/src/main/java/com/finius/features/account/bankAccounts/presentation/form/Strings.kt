package com.finius.features.account.bankAccounts.presentation.form

import com.finius.features.account.bankAccounts.presentation.form.balance.BankAccountBalanceStrings
import com.finius.features.account.bankAccounts.presentation.form.balance.BankAccountBalanceStringsPt
import com.finius.features.account.bankAccounts.presentation.form.brand.BankAccountBrandStrings
import com.finius.features.account.bankAccounts.presentation.form.brand.BankAccountBrandStringsPt
import com.finius.features.account.bankAccounts.presentation.form.name.BankAccountNameStrings
import com.finius.features.account.bankAccounts.presentation.form.name.BankAccountNameStringsPt

data class BankAccountFormStrings(
    val bankAccountBalanceStrings: BankAccountBalanceStrings,
    val bankAccountNameStrings: BankAccountNameStrings,
    val bankAccountBrandStrings: BankAccountBrandStrings,
)

val BankAccountFormStringsPt = BankAccountFormStrings(
    bankAccountBalanceStrings = BankAccountBalanceStringsPt,
    bankAccountNameStrings = BankAccountNameStringsPt,
    bankAccountBrandStrings = BankAccountBrandStringsPt
)