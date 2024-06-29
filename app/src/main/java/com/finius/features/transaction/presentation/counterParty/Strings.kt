package com.finius.features.transaction.presentation.counterParty

import com.finius.core.domain.TransactionType

data class TransactionCounterPartyStrings(
    val title: (TransactionType) -> String,
    val subtitle: (TransactionType) -> String,
    val btnLabel: String,
)

val TransactionCounterPartyStringsPt = TransactionCounterPartyStrings(
    title = { type ->
        when (type) {
            TransactionType.EXPENSE -> "Qual é o destinatário da transação?"
            TransactionType.INCOME -> "Qual a origem da transação?"
        }
    },
    subtitle = { type ->
        when (type) {
            TransactionType.EXPENSE -> "Informe o nome da pessoa ou empresa que recebeu a transação."
            TransactionType.INCOME -> "Informe o nome da pessoa ou empresa que realizou a transação."
        }
    },
    btnLabel = "Continuar"
)