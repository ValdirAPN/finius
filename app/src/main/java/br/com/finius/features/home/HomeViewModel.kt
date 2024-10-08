package br.com.finius.features.home

import androidx.lifecycle.ViewModel
import br.com.finius.data.repository.PaymentAccountRepository
import br.com.finius.data.repository.TransactionRepository
import br.com.finius.domain.model.Money
import br.com.finius.domain.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class HomeUiState(
    val balance: Money = Money(0), val lastTransactions: List<Transaction> = emptyList()
)

class HomeViewModel(
    private val paymentAccountRepository: PaymentAccountRepository,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun assemble() {
        val balance = paymentAccountRepository.getBalance()
        val transactions = transactionRepository.listNewest()
        _uiState.update {
            it.copy(
                balance = balance,
                lastTransactions = transactions
            )
        }
    }
}