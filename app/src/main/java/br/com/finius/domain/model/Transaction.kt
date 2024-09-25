package br.com.finius.domain.model

data class Transaction(
    val id: String,
    val name: String,
    val amount: Money,
    val type: TransactionType,
    val paymentAccountId: String,
    val dateInMilli: Long,
    val installments: Int,
    val party: String,
)
