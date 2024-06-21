package com.finius.core.domain

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
            iconRes: Int = 0
        ) = Account(
            id = id,
            type = type,
            name = name,
            iconRes = iconRes
        )
    }
}
