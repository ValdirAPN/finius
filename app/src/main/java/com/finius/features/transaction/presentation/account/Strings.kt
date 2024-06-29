package com.finius.features.transaction.presentation.account

import com.finius.core.domain.TransactionType

data class TransactionAccountStrings(
    val title: (TransactionType) -> String,
    val subtitle: (TransactionType) -> String,
    val bankAccountsLabel: String,
    val creditCardAccountsLabel: String,
    val btnLabel: String,
)

val TransactionAccountStringsPt = TransactionAccountStrings(
    title = { type ->
        when (type) {
            TransactionType.EXPENSE -> "Em que conta ou cartão deseja cadastrar a transação?"
            TransactionType.INCOME -> "Qual conta recebeu a transação?"
        }
    },
    subtitle = { type ->
        when (type) {
            TransactionType.EXPENSE -> "Selecione a conta ou cartão que foi utilizado para realizar a transação"
            TransactionType.INCOME -> "Selecione a conta para qual a transação foi realizada."
        }
    },
    bankAccountsLabel = "Contas",
    creditCardAccountsLabel = "Cartões",
    btnLabel = "Continuar"
)