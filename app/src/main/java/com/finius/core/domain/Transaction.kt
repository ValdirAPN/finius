package com.finius.core.domain

import com.finius.core.TransactionEntity
import com.finius.features.transaction.presentation.date.TransactionRecurrenceUnit
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

enum class TransactionType {
    INCOME, EXPENSE
}

data class Transaction(
    val id: String,
    val title: String,
    val amount: Long,
    val type: TransactionType,
    val counterParty: String,
    val account: Account,
    val category: Category,
    val date: LocalDate,
    val recurrenceUnit: TransactionRecurrenceUnit,
    val recurrence: Int
) {

    fun getCurrentInstallments(): Int {
        val today = Clock.System.now().toLocalDateTime(TimeZone.UTC)
        val currentMonth = today.monthNumber
        val monthDifference = currentMonth - date.monthNumber

        return recurrence - monthDifference
    }

    companion object {
        fun fakeTransactions() = listOf(
            Transaction(
                id = "1",
                title = "Tênis",
                amount = 37000,
                type = TransactionType.EXPENSE,
                counterParty = "Netshoes",
                account = Account.createFakeAccount(),
                category = Category.fakeCategory(),
                date = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
                recurrenceUnit = TransactionRecurrenceUnit.MONTHLY,
                recurrence = 10
            ),
            Transaction(
                id = "2",
                title = "Ar-condicionado",
                amount = 271000,
                type = TransactionType.EXPENSE,
                counterParty = "Mercado Livre",
                account = Account.createFakeAccount(),
                category = Category.fakeCategory(),
                date = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
                recurrenceUnit = TransactionRecurrenceUnit.MONTHLY,
                recurrence = 10
            ),
            Transaction(
                id = "3",
                title = "Máquina de Lavar",
                amount = 182300,
                type = TransactionType.EXPENSE,
                counterParty = "Magazine Luiza",
                account = Account.createFakeAccount(),
                category = Category.fakeCategory(),
                date = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
                recurrenceUnit = TransactionRecurrenceUnit.MONTHLY,
                recurrence = 6
            ),
            Transaction(
                id = "4",
                title = "Salário",
                amount = 649800,
                type = TransactionType.INCOME,
                counterParty = "Globant",
                account = Account.createFakeAccount(
                    id = "ContaItau",
                    name = "Itaú",
                    brand = AccountBrand.Itau
                ),
                category = Category.fakeCategory(
                    id = "Salary",
                    title = "Salário",
                    icon = CategoryIcon.Currency
                ),
                date = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
                recurrenceUnit = TransactionRecurrenceUnit.MONTHLY,
                recurrence = 1
            )
        )
    }
}

fun TransactionEntity.toTransaction(account: Account, category: Category) = Transaction(
    id = id,
    title = title,
    amount = amount,
    type = type,
    counterParty = counterParty,
    account = account,
    category = category,
    date = Instant.fromEpochMilliseconds(dateMilliseconds).toLocalDateTime(TimeZone.UTC).date,
    recurrenceUnit = recurrenceUnit,
    recurrence = recurrence.toInt()
)

