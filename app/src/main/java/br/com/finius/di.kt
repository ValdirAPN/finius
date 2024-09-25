package br.com.finius

import org.koin.dsl.module

val appModule = module {
    single<FiniusDatabase> {
        Database(get())
            .database
    }

    single<TransactionEntityQueries> {
        inject<FiniusDatabase>()
            .value
            .transactionEntityQueries
    }

    single<PaymentAccountEntityQueries> {
        inject<FiniusDatabase>()
            .value
            .paymentAccountEntityQueries
    }
}