package com.finius.core.data

import com.finius.core.AccountEntityQueries
import com.finius.core.CategoryEntityQueries
import com.finius.core.TransactionEntityQueries
import com.finius.core.domain.TransactionType
import com.finius.core.domain.toAccount
import com.finius.core.domain.toCategory
import com.finius.core.domain.toTransaction
import com.finius.features.transaction.presentation.date.TransactionRecurrenceUnit

class TransactionRepository(
    private val transactionEntityQueries: TransactionEntityQueries,
    private val accountEntityQueries: AccountEntityQueries,
    private val categoryEntityQueries: CategoryEntityQueries
) {

    fun getAll() =
        transactionEntityQueries
            .getAll()
            .executeAsList()
            .map {
                val account = accountEntityQueries
                    .getById(it.accountId)
                    .executeAsOne()
                    .toAccount()

                val category = categoryEntityQueries
                    .getById(it.categoryId)
                    .executeAsOne()
                    .toCategory()

                it.toTransaction(account = account, category = category)
            }

    fun insert(
        id: String,
        title: String,
        amount: Long,
        type: TransactionType,
        counterParty: String,
        accountId: String,
        categoryId: String,
        dateMilliseconds: Long,
        recurrenceUnit: TransactionRecurrenceUnit,
        recurrence: Long
    ) {
        transactionEntityQueries
            .insert(
                id = id,
                title = title,
                amount = amount,
                type = type,
                counterParty = counterParty,
                accountId = accountId,
                categoryId = categoryId,
                dateMilliseconds = dateMilliseconds,
                recurrenceUnit = recurrenceUnit,
                recurrence = recurrence
            )
    }
}