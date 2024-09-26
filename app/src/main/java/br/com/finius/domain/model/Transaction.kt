package br.com.finius.domain.model

data class Transaction(
    val id: String,
    val name: String,
    val amount: Money,
    val type: TransactionType,
    val paymentAccountId: String,
    val date: Date,
    val installments: Int,
    val party: String,
    val category: TransactionCategory,
)
