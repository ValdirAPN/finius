package br.com.finius

import org.koin.dsl.module

val appModule = module {
    single<FiniusDatabase> {
        Database(get())
            .database
    }

    single<TransactionQueries> {
        inject<FiniusDatabase>()
            .value
            .transactionQueries
    }
}