package com.finius.features.home.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import com.finius.core.data.TransactionRepository
import com.finius.core.domain.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class HomeScreenState(
    val transactions: List<Transaction> = emptyList()
)

class HomeScreenModel(
    private val transactionRepository: TransactionRepository
) : ScreenModel {

    private var _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    fun assemble() {
        val transactions = transactionRepository.getAll()
        _uiState.update {
            it.copy(
                transactions = transactions
            )
        }
    }
}