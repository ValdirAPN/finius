package com.finius

import android.app.Application
import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.finius.core.AccountEntity
import com.finius.core.CategoryEntity
import com.finius.core.TransactionEntity
import com.finius.core.data.CategoryRepository
import com.finius.core.data.TransactionRepository
import com.finius.core.domain.AccountBrand
import com.finius.core.domain.AccountType
import com.finius.core.domain.CategoryIcon
import com.finius.core.domain.TransactionType
import com.finius.features.account.bankAccounts.presentation.form.AccountFormScreenModel
import com.finius.features.account.bankAccounts.presentation.home.AccountsHomeScreenModel
import com.finius.features.account.creditCards.presentation.form.CardFormScreenModel
import com.finius.features.account.creditCards.presentation.home.CardsHomeScreenModel
import com.finius.features.account.data.AccountRepository
import com.finius.features.categories.presentation.CategoriesHomeScreenModel
import com.finius.features.categories.presentation.CategoryFormScreenModel
import com.finius.features.home.presentation.HomeScreenModel
import com.finius.features.transaction.presentation.TransactionFormScreenModel
import com.finius.features.transaction.presentation.account.TransactionAccountScreenModel
import com.finius.features.transaction.presentation.category.TransactionCategoryScreenModel
import com.finius.features.transaction.presentation.date.TransactionRecurrenceUnit
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
                TransactionEntityAdapter = TransactionEntityAdapter,
                AccountEntityAdapter = AccountEntityAdapter,
                CategoryEntityAdapter = CategoryEntityAdapter
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

        bindSingleton {
            instance<Database>().categoryEntityQueries
        }

        bindSingleton {
            CategoryRepository(
                categoryEntityQueries = instance()
            )
        }

        bindSingleton {
            CategoryFormScreenModel(
                categoryRepository = instance()
            )
        }

        bindSingleton {
            CategoriesHomeScreenModel(
                categoryRepository = instance()
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

        bindSingleton {
            TransactionFormScreenModel(
                transactionRepository = instance()
            )
        }

        bindSingleton {
            HomeScreenModel(
                transactionRepository = instance()
            )
        }
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