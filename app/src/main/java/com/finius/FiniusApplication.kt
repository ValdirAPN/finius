package com.finius

import android.app.Application
import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.finius.core.AccountEntity
import com.finius.core.domain.AccountBrand
import com.finius.core.domain.AccountType
import com.finius.features.account.data.AccountRepository
import com.finius.features.account.bankAccounts.presentation.form.AccountFormScreenModel
import com.finius.features.account.bankAccounts.presentation.home.AccountsHomeScreenModel
import com.finius.features.account.creditCards.presentation.form.CardFormScreenModel
import com.finius.features.account.creditCards.presentation.home.CardsHomeScreenModel
import com.finius.features.transaction.presentation.account.TransactionAccountScreenModel
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

class FiniusApplication : Application(), DIAware {

    override val di by DI.lazy {

        bindSingleton {
            AndroidSqliteDriver(
                schema = Database.Schema,
                context = this@FiniusApplication,
                name = "Finius.db",
            )
        }

        bindSingleton {
            Database(
                driver = instance<AndroidSqliteDriver>(),
                AccountEntityAdapter = AccountEntity.Adapter(
                    typeAdapter = object : ColumnAdapter<AccountType, String> {
                        override fun decode(databaseValue: String): AccountType {
                            return AccountType.entries.first { it.name == databaseValue }
                        }

                        override fun encode(value: AccountType): String {
                            return value.name
                        }
                    },
                    brandAdapter = object : ColumnAdapter<AccountBrand, String> {
                        override fun decode(databaseValue: String): AccountBrand {
                            return AccountBrand.entries.first { it.name == databaseValue }
                        }

                        override fun encode(value: AccountBrand): String {
                            return value.name
                        }
                    }
                )
            )
        }

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

        bindSingleton {
            AccountFormScreenModel(
                accountRepository = instance()
            )
        }

        bindSingleton {
            CardsHomeScreenModel(
                accountRepository = instance()
            )
        }

        bindSingleton {
            CardFormScreenModel(
                accountRepository = instance()
            )
        }

        bindSingleton {
            TransactionAccountScreenModel(
                accountRepository = instance()
            )
        }
    }
}