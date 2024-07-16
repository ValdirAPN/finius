package com.finius.features.home.presentation

data class HomeStrings(
    val balance: String,
    val newTransactionLabel: String,
    val accountsLabel: String,
    val cardsLabel: String,
    val transactionsOverviewLabel: String,
    val categoriesLabel: String,
)

val HomeStringsPt = HomeStrings(
    balance = "Saldo",
    newTransactionLabel = "Nova transação",
    accountsLabel = "Contas",
    cardsLabel = "Cartões",
    transactionsOverviewLabel = "Resumo de transações",
    categoriesLabel = "Categorias"
)