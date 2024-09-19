package br.com.finius.bankAccounts

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val bankAccountsModule = module {
    viewModelOf(::BankAccountsViewModel)
}