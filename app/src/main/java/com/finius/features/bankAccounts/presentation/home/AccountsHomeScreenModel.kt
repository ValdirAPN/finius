package com.finius.features.bankAccounts.presentation.home

import cafe.adriel.voyager.core.model.ScreenModel
import com.finius.core.domain.Account
import com.finius.features.bankAccounts.data.AccountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AccountsHomeState(
    val accounts: List<Account> = emptyList()
)
data class AccountsHomeScreenModel(
    private val accountRepository: AccountRepository
) : ScreenModel {

    private var _uiState = MutableStateFlow(AccountsHomeState())
    val uiState = _uiState.asStateFlow()

    init {
        assemble()
    }

    private fun assemble() {
        val accounts = accountRepository.getAll()
        _uiState.update {
            it.copy(accounts = accounts)
        }
    }
}