package br.com.finius

import android.content.Context
import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import br.com.finius.domain.model.Colors
import br.com.finius.domain.model.PaymentAccountType
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
        },
        colorAdapter = object : ColumnAdapter<Colors, String> {
            override fun decode(databaseValue: String): Colors =
                Colors.entries.first { it.name == databaseValue }

            override fun encode(value: Colors): String =
                value.name
        }
    )

    private val paymentAccountAdapter = PaymentAccount.Adapter(
        colorAdapter = object : ColumnAdapter<Colors, String> {
            override fun decode(databaseValue: String): Colors =
                Colors.entries.first { it.name == databaseValue }

            override fun encode(value: Colors): String =
                value.name
        },
        typeAdapter = object : ColumnAdapter<PaymentAccountType, String> {
            override fun decode(databaseValue: String): PaymentAccountType =
                PaymentAccountType.entries.first { it.name == databaseValue }

            override fun encode(value: PaymentAccountType): String =
                value.name
        }
    )

    val database = FiniusDatabase(
        driver,
        financialTransactionAdapter = financialTransactionAdapter,
        paymentAccountAdapter = paymentAccountAdapter,
    )
}