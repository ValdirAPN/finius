package com.finius.features.account.creditCards.presentation.home

import cafe.adriel.voyager.core.model.ScreenModel
import com.finius.core.domain.Account
import com.finius.core.domain.AccountType
import com.finius.core.domain.CreditCard
import com.finius.features.account.data.AccountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CardsHomeState(
    val cards: List<Account> = emptyList()
)
data class CardsHomeScreenModel(
    private val accountRepository: AccountRepository
) : ScreenModel {

    private var _uiState = MutableStateFlow(CardsHomeState())
    val uiState = _uiState.asStateFlow()

    fun assemble() {
        val cards = accountRepository.getAllByType(type = AccountType.CreditCard)
        _uiState.update {
            it.copy(cards = cards)
        }
    }
}