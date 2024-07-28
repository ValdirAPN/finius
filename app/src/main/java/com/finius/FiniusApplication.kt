package com.finius

import android.app.Application
import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.finius.core.AccountEntity
import com.finius.core.CategoryEntity
import com.finius.core.TransactionEntity
import com.finius.core.domain.AccountBrand
import com.finius.core.domain.AccountType
import com.finius.core.domain.CategoryIcon
import com.finius.core.domain.TransactionType
import com.finius.features.account.accountDi
import com.finius.features.categories.categoryDi
import com.finius.features.home.homeDi
import com.finius.features.transaction.presentation.date.TransactionRecurrenceUnit
import com.finius.features.transaction.transactionDi
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

private const val DATABASE_NAME = "Finius.db"

class FiniusApplication : Application(), DIAware {

    override val di by DI.lazy {

        bindSingleton {
            AndroidSqliteDriver(
                schema = Database.Schema,
                context = this@FiniusApplication,
                name = DATABASE_NAME,
            )
        }

        bindSingleton {
            Database(
                driver = instance<AndroidSqliteDriver>(),
                TransactionEntityAdapter = TransactionEntityAdapter,
                AccountEntityAdapter = AccountEntityAdapter,
                CategoryEntityAdapter = CategoryEntityAdapter
            )
        }

        bindProvider {
            SplashScreenModel(
                categoryRepository = instance()
            )
        }

        import(homeDi)
        import(accountDi)
        import(transactionDi)
        import(categoryDi)
    }
}

private val TransactionEntityAdapter = TransactionEntity.Adapter(
    typeAdapter = object : ColumnAdapter<TransactionType, String> {
        override fun decode(databaseValue: String): TransactionType {
            return TransactionType.entries.first { it.name == databaseValue }
        }

        override fun encode(value: TransactionType): String {
            return value.name
        }
    },
    recurrenceUnitAdapter = object : ColumnAdapter<TransactionRecurrenceUnit, String> {
        override fun decode(databaseValue: String): TransactionRecurrenceUnit {
            return TransactionRecurrenceUnit.entries.first { it.name == databaseValue }
        }

        override fun encode(value: TransactionRecurrenceUnit): String {
            return value.name
        }
    }
)

private val CategoryEntityAdapter = CategoryEntity.Adapter(
    iconAdapter = object : ColumnAdapter<CategoryIcon, String> {
        override fun decode(databaseValue: String): CategoryIcon {
            return CategoryIcon.entries.first { it.name == databaseValue }
        }

        override fun encode(value: CategoryIcon): String {
            return value.name
        }
    }
)

private val AccountEntityAdapter = AccountEntity.Adapter(
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