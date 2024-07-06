package com.finius

import android.app.Application
import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.finius.core.AccountEntity
import com.finius.core.domain.AccountBrand
import com.finius.core.domain.AccountType
import com.finius.features.bankAccounts.data.AccountRepository
import com.finius.features.bankAccounts.presentation.form.AccountFormScreenModel
import com.finius.features.bankAccounts.presentation.home.AccountsHomeScreenModel
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
    }
}