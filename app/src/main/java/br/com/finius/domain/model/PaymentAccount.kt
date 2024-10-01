package br.com.finius.domain.model

data class PaymentAccount(
    val id: String,
    val name: String,
    val balance: Money,
    val type: PaymentAccountType,
    val dueDay: Int?,
    val closingDay: Int?,
    val color: Colors
)
