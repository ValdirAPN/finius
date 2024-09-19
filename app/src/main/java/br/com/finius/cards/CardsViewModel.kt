package br.com.finius.cards

import androidx.lifecycle.ViewModel
import br.com.finius.data.repository.PaymentAccountRepository
import br.com.finius.domain.model.PaymentAccount
import br.com.finius.domain.model.PaymentAccountType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CardsUiState(
    val cards: List<PaymentAccount> = emptyList()
)
class CardsViewModel(
    private val paymentAccountRepository: PaymentAccountRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(CardsUiState())
    val uiState = _uiState.asStateFlow()

    fun getAccounts() {
        val cards = paymentAccountRepository.getAccountsByType(PaymentAccountType.Card)
        _uiState.update { it.copy(cards = cards) }
    }
}