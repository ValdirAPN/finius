package com.finius.features.account.data

import com.finius.core.AccountEntityQueries
import com.finius.core.domain.AccountBrand
import com.finius.core.domain.AccountType
import com.finius.core.domain.toAccount

class AccountRepository(
    private val accountEntityQueries: AccountEntityQueries
) {

    fun getAll() = accountEntityQueries
        .getAll()
        .executeAsList()
        .map { it.toAccount() }

    fun getAllByType(type: AccountType) = accountEntityQueries
        .getByType(type = type)
        .executeAsList()
        .map { it.toAccount() }

    fun insert(
        id: String,
        type: AccountType,
        name: String,
        brand: AccountBrand,
        balance: Double,
        totalLimit: Double? = null,
        dueDay: Int? = null,
    ) = accountEntityQueries.insert(
            id = id,
            type = type,
            name = name,
            brand = brand,
            balance = balance,
            totalLimit = totalLimit,
            dueDay = dueDay?.toLong()
        )
}