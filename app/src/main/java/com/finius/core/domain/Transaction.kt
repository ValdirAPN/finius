package com.finius.core.domain

import com.finius.R
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

enum class RecurrenceType {
    DAILY, WEEKLY, BIWEEKLY, MONTHLY, YEARLY
}

enum class TransactionType {
    INCOME, EXPENSE
}

data class Transaction(
    val id: String,
    val title: String,
    val amount: Double,
    val type: TransactionType,
    val counterParty: String,
    val account: Account,
    val category: Category,
    val date: LocalDate,
    val recurrenceType: RecurrenceType,
    val repeatTimes: Int
) {
    companion object {
        fun fakeTransactions() = listOf(
            Transaction(
                id = "1",
                title = "Tênis",
                amount = 370.0,
                type = TransactionType.EXPENSE,
                counterParty = "Netshoes",
                account = Account.fakeAccount(),
                category = Category.fakeCategory(),
                date = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
                recurrenceType = RecurrenceType.MONTHLY,
                repeatTimes = 10
            ),
            Transaction(
                id = "2",
                title = "Ar-condicionado",
                amount = 2710.0,
                type = TransactionType.EXPENSE,
                counterParty = "Mercado Livre",
                account = Account.fakeAccount(),
                category = Category.fakeCategory(),
                date = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
                recurrenceType = RecurrenceType.MONTHLY,
                repeatTimes = 10
            ),
            Transaction(
                id = "3",
                title = "Máquina de Lavar",
                amount = 1823.0,
                type = TransactionType.EXPENSE,
                counterParty = "Magazine Luiza",
                account = Account.fakeAccount(),
                category = Category.fakeCategory(),
                date = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
                recurrenceType = RecurrenceType.MONTHLY,
                repeatTimes = 6
            ),
            Transaction(
                id = "4",
                title = "Salário",
                amount = 6498.0,
                type = TransactionType.INCOME,
                counterParty = "Globant",
                account = Account.fakeAccount(
                    id = "ContaItau",
                    type = AccountType.BANK_ACCOUNT,
                    name = "Itaú",
                    iconRes = 0
                ),
                category = Category.fakeCategory(
                    id = "Salary",
                    title = "Salário",
                    iconRes = R.drawable.currency_dolar_fill
                ),
                date = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
                recurrenceType = RecurrenceType.MONTHLY,
                repeatTimes = 1
            )
        )
    }
}



