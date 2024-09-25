package br.com.finius.features.transactionList

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val transactionListModule = module {
    viewModelOf(::TransactionListViewModel)
}