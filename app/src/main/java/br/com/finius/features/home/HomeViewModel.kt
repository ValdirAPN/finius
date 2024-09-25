package br.com.finius.features.home

import androidx.lifecycle.ViewModel
import br.com.finius.data.repository.TransactionRepository
import br.com.finius.domain.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class HomeUiState(
    val lastTransactions: List<Transaction> = emptyList()
)
class HomeViewModel(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun getTransactions() {
        val transactions = transactionRepository.getTransactions()
        _uiState.update { it.copy(lastTransactions = transactions) }
    }
}