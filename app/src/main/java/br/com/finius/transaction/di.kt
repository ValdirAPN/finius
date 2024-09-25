package br.com.finius.transaction

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val newTransactionModule = module {
    viewModelOf(::NewTransactionViewModel)
}