package br.com.finius

import android.content.Context
import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import br.com.finius.domain.model.TransactionType

class Database(
    private val context: Context
) {
    private val driver = AndroidSqliteDriver(FiniusDatabase.Schema, context, "finius.db")

    private val financialTransactionAdapter = FinancialTransaction.Adapter(
        typeAdapter = object : ColumnAdapter<TransactionType, String> {
            override fun decode(databaseValue: String): TransactionType =
                TransactionType.entries.first { it.name == databaseValue }


            override fun encode(value: TransactionType): String =
                value.name
        }
    )


    val database = FiniusDatabase(
        driver,
        financialTransactionAdapter = financialTransactionAdapter
    )
}