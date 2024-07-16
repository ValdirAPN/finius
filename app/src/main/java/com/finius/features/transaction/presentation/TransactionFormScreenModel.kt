package com.finius.features.transaction.presentation

import androidx.compose.foundation.text.input.TextFieldState
import cafe.adriel.voyager.core.model.ScreenModel
import com.finius.core.data.TransactionRepository
import com.finius.core.domain.Account
import com.finius.core.domain.Category
import com.finius.core.domain.TransactionType
import com.finius.features.transaction.presentation.date.TransactionRecurrenceUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import java.util.UUID

data class TransactionFormState(
    val type: TransactionType = TransactionType.EXPENSE,
    val account: Account? = null,
    val category: Category? = null,
    val date: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
    val shouldRepeat: Boolean = false,
    val recurrenceUnit: TransactionRecurrenceUnit = TransactionRecurrenceUnit.MONTHLY,
    val recurrence: Int = 1,
    val counterParty: TextFieldState = TextFieldState(),
    val title: TextFieldState = TextFieldState(),
    val amount: TextFieldState = TextFieldState(),
    val isTotalAmount: Boolean = true
)

class TransactionFormScreenModel(
    private val transactionRepository: TransactionRepository
) : ScreenModel {

    private var _uiState = MutableStateFlow(TransactionFormState())
    val uiState = _uiState.asStateFlow()

    fun setType(type: TransactionType) {
        _uiState.update {
            it.copy(type = type)
        }
    }

    fun setAccount(account: Account) {
        _uiState.update {
            it.copy(account = account)
        }
    }

    fun setCategory(category: Category) {
        _uiState.update {
            it.copy(category = category)
        }
    }

    fun setDate(date: LocalDate) {
        _uiState.update {
            it.copy(date = date)
        }
    }

    fun setShouldRepeat(shouldRepeat: Boolean) {
        _uiState.update {
            it.copy(shouldRepeat = shouldRepeat)
        }
    }

    fun setIsTotalAmount(isTotalAmount: Boolean) {
        _uiState.update {
            it.copy(isTotalAmount = isTotalAmount)
        }
    }

    fun setRecurrenceUnit(unit: TransactionRecurrenceUnit) {
        _uiState.update {
            it.copy(recurrenceUnit = unit)
        }
    }

    fun setRecurrence(recurrence: Int) {
        _uiState.update {
            it.copy(recurrence = recurrence)
        }
    }

    fun createTransaction() {
        with(_uiState.value) {
            transactionRepository.insert(
                id = UUID.randomUUID().toString(),
                title = title.text.toString(),
                amount = amount.text.toString().toDouble(),
                type = type,
                counterParty = counterParty.text.toString(),
                accountId = account?.id ?: throw Exception("Account is null"),
                categoryId = category?.id ?: throw Exception("Category is null"),
                dateMilliseconds = date.atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds(),
                recurrenceUnit = recurrenceUnit,
                recurrence = recurrence.toLong()
            )
        }
    }
}