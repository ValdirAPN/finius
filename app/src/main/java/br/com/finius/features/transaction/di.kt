package br.com.finius.features.transaction

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val newTransactionModule = module {
    viewModelOf(::NewTransactionViewModel)
}