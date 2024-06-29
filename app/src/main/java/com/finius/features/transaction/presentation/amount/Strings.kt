package com.finius.features.transaction.presentation.amount

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

data class TransactionAmountStrings(
    val title: String,
    val installmentValueLabel: String,
    val totalValueLabel: String,
    val installment: String,
    val total: String,
    val isTotalAmountLabel: AnnotatedString,
    val btnLabel: String
)

val TransactionAmountStringsPt = TransactionAmountStrings(
    title = "Qual o valor da transação?",
    installmentValueLabel = "Valor da parcela",
    totalValueLabel = "Valor total",
    installment = "Parcela",
    total = "Total",
    isTotalAmountLabel = buildAnnotatedString {
        append("Esse é o ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("valor total")
        }
        append("?")
    },
    btnLabel = "Cadastrar"
)