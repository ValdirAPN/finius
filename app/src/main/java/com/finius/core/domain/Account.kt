package com.finius.core.domain

import com.finius.R

enum class AccountType {
    BANK_ACCOUNT, CREDIT_CARD
}
data class Account(
    val id: String,
    val type: AccountType,
    val name: String,
    val iconRes: Int,
) {
    companion object {
        fun fakeAccount(
            id: String = "id",
            type: AccountType = AccountType.CREDIT_CARD,
            name: String = "Nubank",
            iconRes: Int = R.drawable.nubank
        ) = Account(
            id = id,
            type = type,
            name = name,
            iconRes = iconRes
        )

        fun fakeAccounts() = listOf(
            fakeAccount(
                id = "1",
            ),
            fakeAccount(
                id = "2",
                type = AccountType.BANK_ACCOUNT,
                name = "Banco do Brasil",
                iconRes = R.drawable.banco_do_brasil
            ),
            fakeAccount(
                id = "3",
                type = AccountType.BANK_ACCOUNT,
                name = "Itaú",
                iconRes = R.drawable.itau
            ),
            fakeAccount(
                id = "4",
                type = AccountType.BANK_ACCOUNT,
                name = "C6",
                iconRes = R.drawable.c6
            ),
            fakeAccount(
                id = "5",
                type = AccountType.CREDIT_CARD,
                name = "C6",
                iconRes = R.drawable.c6
            ),
        )
    }
}
