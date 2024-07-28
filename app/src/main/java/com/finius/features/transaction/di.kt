package com.finius.features.transaction

import com.finius.Database
import com.finius.core.data.TransactionRepository
import com.finius.features.transaction.presentation.TransactionFormScreenModel
import com.finius.features.transaction.presentation.account.TransactionAccountScreenModel
import com.finius.features.transaction.presentation.category.TransactionCategoryScreenModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val transactionDi = DI.Module("transaction-module") {
    bindProvider {
        TransactionAccountScreenModel(
            accountRepository = instance()
        )
    }

    bindSingleton {
        instance<Database>().transactionEntityQueries
    }

    bindProvider {
        TransactionCategoryScreenModel(
            categoryRepository = instance()
        )
    }

    bindSingleton {
        TransactionRepository(
            transactionEntityQueries = instance(),
            accountEntityQueries = instance(),
            categoryEntityQueries = instance()
        )
    }

    bindProvider {
        TransactionFormScreenModel(
            transactionRepository = instance()
        )
    }
}