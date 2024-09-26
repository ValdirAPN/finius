@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.finius.features.transaction

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import br.com.finius.data.repository.PaymentAccountRepository
import br.com.finius.data.repository.TransactionRepository
import br.com.finius.domain.model.Date
import br.com.finius.domain.model.Money
import br.com.finius.domain.model.PaymentAccount
import br.com.finius.domain.model.PaymentAccountType
import br.com.finius.domain.model.Transaction
import br.com.finius.domain.model.TransactionCategory
import br.com.finius.domain.model.TransactionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import java.util.Locale
import java.util.UUID

data class NewTransactionUiState(
    val cards: List<PaymentAccount> = emptyList(),
    val banks: List<PaymentAccount> = emptyList(),
    val type: TransactionType = TransactionType.EXPENSE,
    val title: TextFieldState = TextFieldState(),
    val amount: TextFieldState = TextFieldState(),
    val paymentAccount: PaymentAccount? = null,
    val installments: Int = 1,
    val party: TextFieldState = TextFieldState(),
    val category: TransactionCategory = TransactionCategory.OTHERS,
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
        val cards = paymentAccountRepository.getAccountsByType(PaymentAccountType.CARD)
        _uiState.update { it.copy(cards = cards) }
    }

    fun getBanks() {
        val banks = paymentAccountRepository.getAccountsByType(PaymentAccountType.BANK)
        _uiState.update { it.copy(banks = banks) }
    }

    fun setTransactionType(transactionType: TransactionType) {
        _uiState.update { it.copy(type = transactionType) }
    }

    fun setPaymentAccount(paymentAccount: PaymentAccount) {
        _uiState.update { it.copy(paymentAccount = paymentAccount) }
    }

    fun setCategory(category: TransactionCategory) {
        _uiState.update { it.copy(category = category) }
    }

    fun setInstallments(value: Int) {
        _uiState.update { it.copy(installments = value) }
    }

    fun createTransaction() = with(_uiState.value) {
        transactionRepository.create(
            Transaction(
                id = UUID.randomUUID().toString(),
                name = title.text.toString(),
                amount = Money(amount.text.toString().toLongOrNull() ?: 0L),
                date = Date(dateState.selectedDateMillis ?: 0L),
                installments = installments,
                party = party.text.toString(),
                type = type,
                paymentAccountId = paymentAccount?.id ?: "",
                category = category
            )
        )
    }
}