package com.finius.features.transaction.presentation.type

data class TransactionTypeStrings(
    val title: String,
    val rbIncomeLabel: String,
    val rbExpenseLabel: String,
    val btnLabel: String,
)

val TransactionTypeStringsPt = TransactionTypeStrings(
    title = "Qual é o tipo da transação?",
    rbIncomeLabel = "Receita",
    rbExpenseLabel = "Despesa",
    btnLabel = "Continuar"
)