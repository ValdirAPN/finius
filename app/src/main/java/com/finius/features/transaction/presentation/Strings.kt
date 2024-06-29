package com.finius.features.transaction.presentation

import com.finius.features.transaction.presentation.account.TransactionAccountStrings
import com.finius.features.transaction.presentation.account.TransactionAccountStringsPt
import com.finius.features.transaction.presentation.amount.TransactionAmountScreen
import com.finius.features.transaction.presentation.amount.TransactionAmountStrings
import com.finius.features.transaction.presentation.amount.TransactionAmountStringsPt
import com.finius.features.transaction.presentation.category.TransactionCategoryStrings
import com.finius.features.transaction.presentation.category.TransactionCategoryStringsPt
import com.finius.features.transaction.presentation.counterParty.TransactionCounterPartyStrings
import com.finius.features.transaction.presentation.counterParty.TransactionCounterPartyStringsPt
import com.finius.features.transaction.presentation.date.TransactionRecurrenceStrings
import com.finius.features.transaction.presentation.date.TransactionRecurrenceStringsPt
import com.finius.features.transaction.presentation.date.TransactionDateStrings
import com.finius.features.transaction.presentation.date.TransactionDateStringsPt
import com.finius.features.transaction.presentation.title.TransactionTitleStrings
import com.finius.features.transaction.presentation.title.TransactionTitleStringsPt
import com.finius.features.transaction.presentation.type.TransactionTypeStrings
import com.finius.features.transaction.presentation.type.TransactionTypeStringsPt

data class TransactionStrings(
    val successScreenTitle: String,
    val typeStrings: TransactionTypeStrings,
    val counterPartyStrings: TransactionCounterPartyStrings,
    val accountStrings: TransactionAccountStrings,
    val titleStrings: TransactionTitleStrings,
    val categoryStrings: TransactionCategoryStrings,
    val dateStrings: TransactionDateStrings,
    val recurrenceStrings: TransactionRecurrenceStrings,
    val amountStrings: TransactionAmountStrings
)

val TransactionStringsPt = TransactionStrings(
    successScreenTitle = "Transação cadastrada com sucesso!",
    typeStrings = TransactionTypeStringsPt,
    counterPartyStrings = TransactionCounterPartyStringsPt,
    accountStrings = TransactionAccountStringsPt,
    titleStrings = TransactionTitleStringsPt,
    categoryStrings = TransactionCategoryStringsPt,
    dateStrings = TransactionDateStringsPt,
    recurrenceStrings = TransactionRecurrenceStringsPt,
    amountStrings = TransactionAmountStringsPt
)
