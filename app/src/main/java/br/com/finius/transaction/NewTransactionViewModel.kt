@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.finius.transaction

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import br.com.finius.data.repository.PaymentAccountRepository
import br.com.finius.data.repository.TransactionRepository
import br.com.finius.domain.model.PaymentAccount
import br.com.finius.domain.model.PaymentAccountType
import br.com.finius.domain.model.Transaction
import br.com.finius.domain.model.TransactionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import java.util.Locale
import java.util.UUID

data class NewTransactionUiState(
    val cards: List<PaymentAccount> = emptyList(),
    val type: TransactionType = TransactionType.Expense,
    val title: TextFieldState = TextFieldState(),
    val amount: TextFieldState = TextFieldState(),
    val paymentAccountType: PaymentAccountType = PaymentAccountType.Card,
    val paymentAccount: PaymentAccount? = null,
    val installments: TextFieldState = TextFieldState(),
    val party: TextFieldState = TextFieldState(),
    val dateState: DatePickerState = DatePickerState(
        Locale.getDefault(),
        initialSelectedDateMillis = Clock.System.now().toEpochMilliseconds()
    ),
)

class NewTransactionViewModel(
    private val transactionRepository: TransactionRepository,
    private val paymentAccountRepository: PaymentAccountRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(NewTransactionUiState())
    val uiState = _uiState.asStateFlow()

    fun getCards() {
        val cards = paymentAccountRepository.getAccountsByType(PaymentAccountType.Card)
        _uiState.update { it.copy(cards = cards) }
    }

    fun setTransactionType(transactionType: TransactionType) {
        _uiState.update { it.copy(type = transactionType) }
    }

    fun setPaymentAccountType(paymentAccountType: PaymentAccountType) {
        _uiState.update { it.copy(paymentAccountType = paymentAccountType) }
    }

    fun setPaymentAccount(paymentAccount: PaymentAccount) {
        _uiState.update { it.copy(paymentAccount = paymentAccount) }
    }

    fun createTransaction() = with(_uiState.value) {
        transactionRepository.create(
            Transaction(
                id = UUID.randomUUID().toString(),
                name = title.text.toString(),
                amount = amount.text.toString().toDouble(),
                dateInMilli = dateState.selectedDateMillis ?: 0L,
                installments = installments.text.toString().toInt(),
                party = party.text.toString(),
                type = type,
                paymentAccountId = paymentAccount?.id ?: "",
            )
        )
    }
}