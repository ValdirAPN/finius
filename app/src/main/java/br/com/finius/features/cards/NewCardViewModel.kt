package br.com.finius.features.cards

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

data class NewCardUiState(
    val name: TextFieldState = TextFieldState(),
    val balance: TextFieldState = TextFieldState(),
    val dueDay: TextFieldState = TextFieldState(),
    val closingDay: TextFieldState = TextFieldState(),
    val color: Colors = Colors.entries.first(),
)

class NewCardViewModel(
    private val paymentAccountRepository: PaymentAccountRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(NewCardUiState())
    val uiState = _uiState.asStateFlow()

    fun create() = with(_uiState.value) {
        paymentAccountRepository.create(
            PaymentAccount(
                id = UUID.randomUUID().toString(),
                name = name.text.toString().trim(),
                balance = Money(balance.text.toString().toLongOrNull() ?: 0L),
                type = PaymentAccountType.CARD,
                dueDay = dueDay.text.toString().toIntOrNull(),
                closingDay = closingDay.text.toString().toIntOrNull(),
                color = color,
            )
        )
    }

    fun setColor(color: Colors) = _uiState.update { it.copy(color = color) }
}