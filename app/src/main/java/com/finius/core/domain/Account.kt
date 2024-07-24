package com.finius.core.domain

import com.finius.core.AccountEntity
import java.util.UUID

enum class AccountType {
    BankAccount, CreditCard
}

interface Account {
    val id: String
    val name: String
    val brand: AccountBrand
    val balance: Long

    companion object {
        fun createFakeAccount(
            id: String = UUID.randomUUID().toString(),
            name: String = "Nubank",
            brand: AccountBrand = AccountBrand.Nubank,
            balance: Long = 187182
        ) =
            BankAccount(
                id = id,
                name = name,
                brand = brand,
                balance = balance
            )

        fun createFakeAccounts() = listOf(
            BankAccount(
                id = UUID.randomUUID().toString(),
                name = "Nubank",
                brand = AccountBrand.Nubank,
                balance = 1000
            ),
            CreditCard(
                id = UUID.randomUUID().toString(),
                name = "Nubank",
                brand = AccountBrand.Nubank,
                balance = 287182,
                totalLimit = 3000.0,
                dueDay = 4
            ),
            BankAccount(
                id = UUID.randomUUID().toString(),
                name = "Caixa Econômica",
                brand = AccountBrand.CaixaEconomica,
                balance = 292038
            ),
        )
    }
}

data class BankAccount(
    override val id: String,
    override val name: String,
    override val brand: AccountBrand,
    override val balance: Long,
) : Account

data class CreditCard(
    override val id: String,
    override val name: String,
    override val brand: AccountBrand,
    override val balance: Long,
    val totalLimit: Double,
    val dueDay: Int,
) : Account

fun AccountEntity.toAccount() = when(type) {
    AccountType.BankAccount ->
        BankAccount(
            id = id,
            name = name,
            brand = brand,
            balance = balance,
        )

    AccountType.CreditCard ->
        CreditCard(
            id = id,
            name = name,
            brand = brand,
            balance = balance,
            totalLimit = totalLimit ?: 0.0,
            dueDay = dueDay?.toInt() ?: 1,
        )
}
