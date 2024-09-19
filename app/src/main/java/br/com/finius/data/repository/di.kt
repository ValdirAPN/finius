package br.com.finius.data.repository

import org.koin.dsl.module

val repositoryModule = module {
    single { TransactionRepository(get()) }
    single { PaymentAccountRepository(get()) }
}