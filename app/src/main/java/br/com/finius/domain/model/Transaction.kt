package br.com.finius.domain.model

data class Transaction(
    val name: String,
    val amount: Double,
    val type: TransactionType
)
