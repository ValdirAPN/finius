package com.finius.features.transaction.presentation.account

import cafe.adriel.voyager.core.model.ScreenModel
import com.finius.core.domain.Account
import com.finius.core.domain.AccountType
import com.finius.core.domain.TransactionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TransactionAccountScreenState(
    val accounts: List<Account> = emptyList()
)

class TransactionAccountScreenModel : ScreenModel {

    private var _state = MutableStateFlow(TransactionAccountScreenState())
    val state = _state.asStateFlow()

    fun assemble(transactionType: TransactionType) {
        val accounts = Account.fakeAccounts().run {
            if (transactionType == TransactionType.INCOME) {
                filter { it.type != AccountType.CREDIT_CARD }
            } else { this }
        }

        _state.update {
            it.copy(
                accounts = accounts
            )
        }
    }
}