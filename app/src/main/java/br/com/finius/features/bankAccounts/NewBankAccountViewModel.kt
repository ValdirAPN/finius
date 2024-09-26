package br.com.finius.features.bankAccounts

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import br.com.finius.data.repository.PaymentAccountRepository
import br.com.finius.domain.model.Colors
import br.com.finius.domain.model.Money
import br.com.finius.domain.model.PaymentAccount
import br.com.finius.domain.model.PaymentAccountType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

data class NewBankAccountUiState(
    val name: TextFieldState = TextFieldState(),
    val balance: TextFieldState = TextFieldState(),
    val color: Colors = Colors.entries.first(),
)

class NewBankAccountViewModel(
    private val paymentAccountRepository: PaymentAccountRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(NewBankAccountUiState())
    val uiState = _uiState.asStateFlow()

    fun create() = with(_uiState.value) {
        paymentAccountRepository.create(
            PaymentAccount(
                id = UUID.randomUUID().toString(),
                name = name.text.toString().trim(),
                balance = Money(balance.text.toString().toLongOrNull() ?: 0L),
                type = PaymentAccountType.BANK,
                dueDay = null,
                color = color,
            )
        )
    }

    fun setColor(color: Colors) = _uiState.update { it.copy(color = color) }
}