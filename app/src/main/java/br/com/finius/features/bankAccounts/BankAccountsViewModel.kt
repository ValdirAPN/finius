package br.com.finius.features.bankAccounts

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.finius.data.repository.PaymentAccountRepository
import br.com.finius.domain.model.PaymentAccount
import br.com.finius.domain.model.PaymentAccountType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class BankAccountsUiState(
    val accounts: List<PaymentAccount> = emptyList()
)
class BankAccountsViewModel(
    private val paymentAccountRepository: PaymentAccountRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(BankAccountsUiState())
    val uiState = _uiState.asStateFlow()

    fun getAccounts() {
        val accounts = paymentAccountRepository.getAccountsByType(PaymentAccountType.BANK)
        Log.d("FiniusLog", "getAccounts: $accounts")
        _uiState.update { it.copy(accounts = accounts) }
    }
}