package br.com.finius.domain.model

data class PaymentAccount(
    val id: String,
    val name: String,
    val balance: Double,
    val type: PaymentAccountType,
    val dueDay: Int?,
    val color: Colors
)
