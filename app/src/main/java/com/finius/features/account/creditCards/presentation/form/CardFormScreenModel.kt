package com.finius.features.account.creditCards.presentation.form

import androidx.compose.foundation.text.input.TextFieldState
import cafe.adriel.voyager.core.model.ScreenModel
import com.finius.core.domain.AccountBrand
import com.finius.core.domain.AccountType
import com.finius.features.account.data.AccountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

data class CardFormScreenState(
    val brand: AccountBrand? = null,
    val name: TextFieldState = TextFieldState(),
    val limit: TextFieldState = TextFieldState(),
    val dueDay: TextFieldState = TextFieldState(),
)

data class CardFormScreenModel(
    private val accountRepository: AccountRepository
): ScreenModel {

    private var _uiState = MutableStateFlow(CardFormScreenState())
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
                type = AccountType.CreditCard,
                name = name.text.toString(),
                brand = brand ?: throw Error("Brand is null"),
                balance = limit.text.toString().toDouble() / 100,
                totalLimit = limit.text.toString().toDouble() / 100,
                dueDay = dueDay.text.toString().toInt(),
            )
        }
    }
}