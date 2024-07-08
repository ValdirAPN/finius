package com.finius.features.account.bankAccounts.presentation.form

import androidx.compose.foundation.text.input.TextFieldState
import cafe.adriel.voyager.core.model.ScreenModel
import com.finius.core.domain.AccountBrand
import com.finius.core.domain.AccountType
import com.finius.features.account.data.AccountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

data class AccountFormScreenState(
    val brand: AccountBrand = AccountBrand.Wallet,
    val name: TextFieldState = TextFieldState(),
    val balance: TextFieldState = TextFieldState(),
)

data class AccountFormScreenModel(
    private val accountRepository: AccountRepository
): ScreenModel {

    private var _uiState = MutableStateFlow(AccountFormScreenState())
    val uiState = _uiState.asStateFlow()

    fun setBrand(brand: AccountBrand) {
        _uiState.update {
            it.copy(brand = brand)
        }
    }

    fun createAccount() {
        with(_uiState.value) {
            accountRepository.insert(
                id = UUID.randomUUID().toString(),
                type = AccountType.BankAccount,
                name = name.text.toString(),
                brand = brand,
                balance = balance.text.toString().toDouble() / 100,
            )
        }
    }
}