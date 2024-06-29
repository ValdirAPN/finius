package com.finius.features.transaction.presentation.date

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import cafe.adriel.lyricist.strings

data class TransactionRecurrenceStrings(
    val daily: String,
    val dailyUnit: String,
    val weekly: String,
    val weeklyUnit: String,
    val monthly: String,
    val monthlyUnit: String,
    val bimonthly: String,
    val bimonthlyUnit: String,
    val quarterly: String,
    val quarterlyUnit: String,
    val semester: String,
    val semesterUnit: String,
    val yearly: String,
    val yearlyUnit: String
)

val TransactionRecurrenceStringsPt = TransactionRecurrenceStrings(
    daily = "Diário",
    dailyUnit = "dias",
    weekly = "Semanal",
    weeklyUnit = "semanas",
    monthly = "Mensal",
    monthlyUnit = "meses",
    bimonthly = "Bimestral",
    bimonthlyUnit = "bimestres",
    quarterly = "Trimestral",
    quarterlyUnit = "trimestres",
    semester = "Semestral",
    semesterUnit = "semestres",
    yearly = "Anual",
    yearlyUnit = "anos",
)

enum class TransactionRecurrenceUnit(
    val displayName: @Composable () -> String,
    val timeUnit: @Composable () -> String,
) {
    DAILY(
        displayName = { strings.transactionStrings.recurrenceStrings.daily },
        timeUnit = { strings.transactionStrings.recurrenceStrings.dailyUnit }
    ),
    WEEKLY(
        displayName = { strings.transactionStrings.recurrenceStrings.weekly },
        timeUnit = { strings.transactionStrings.recurrenceStrings.weeklyUnit }
    ),
    MONTHLY(
        displayName = { strings.transactionStrings.recurrenceStrings.monthly },
        timeUnit = { strings.transactionStrings.recurrenceStrings.monthlyUnit }
    ),
    BIMONTHLY(
        displayName = { strings.transactionStrings.recurrenceStrings.bimonthly },
        timeUnit = { strings.transactionStrings.recurrenceStrings.bimonthlyUnit }
    ),
    QUARTERLY(
        displayName = { strings.transactionStrings.recurrenceStrings.quarterly },
        timeUnit = { strings.transactionStrings.recurrenceStrings.quarterlyUnit }
    ),
    SEMESTER(
        displayName = { strings.transactionStrings.recurrenceStrings.semester },
        timeUnit = { strings.transactionStrings.recurrenceStrings.semesterUnit }
    ),
    YEARLY(
        displayName = { strings.transactionStrings.recurrenceStrings.yearly },
        timeUnit = { strings.transactionStrings.recurrenceStrings.yearlyUnit }
    )
}

data class TransactionDateStrings(
    val title: String,
    val shouldRepeatLabel: AnnotatedString,
    val howToRepeatLabel: String,
    val howLongToRepeatLabel: @Composable (transactionRecurrenceUnit: TransactionRecurrenceUnit) -> AnnotatedString,
    val btnLabel: String,
)

val TransactionDateStringsPt = TransactionDateStrings(
    title = "Qual a data da transação?",
    shouldRepeatLabel = buildAnnotatedString {
        append("Essa transação se ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("repete")
        }
        append(" ou foi ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("parcelada")
        }
        append("?")
    },
    howToRepeatLabel = "Como ela se repete?",
    howLongToRepeatLabel = { recurrenceUnit -> buildAnnotatedString {
        append("Por quantos ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(recurrenceUnit.timeUnit())
        }
        append("?")
    } },
    btnLabel = "Continuar"
)