package com.finius.features.account

import com.finius.Database
import com.finius.features.account.bankAccounts.presentation.form.AccountFormScreenModel
import com.finius.features.account.bankAccounts.presentation.home.AccountsHomeScreenModel
import com.finius.features.account.creditCards.presentation.form.CardFormScreenModel
import com.finius.features.account.creditCards.presentation.home.CardsHomeScreenModel
import com.finius.features.account.data.AccountRepository
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val accountDi = DI.Module("account-module") {
    bindSingleton {
        instance<Database>().accountEntityQueries
    }

    bindSingleton {
        AccountRepository(
            accountEntityQueries = instance()
        )
    }

    bindProvider {
        AccountsHomeScreenModel(
            accountRepository = instance()
        )
    }

    bindProvider {
        AccountFormScreenModel(
            accountRepository = instance()
        )
    }

    bindProvider {
        CardsHomeScreenModel(
            accountRepository = instance()
        )
    }

    bindProvider {
        CardFormScreenModel(
            accountRepository = instance()
        )
    }
}