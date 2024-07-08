package com.finius.features.transaction.presentation.account

import cafe.adriel.voyager.core.model.ScreenModel
import com.finius.core.domain.Account
import com.finius.core.domain.AccountType
import com.finius.core.domain.BankAccount
import com.finius.core.domain.TransactionType
import com.finius.features.account.data.AccountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TransactionAccountScreenState(
    val accounts: List<Account> = emptyList()
)

class TransactionAccountScreenModel(
    private val accountRepository: AccountRepository
) : ScreenModel {

    private var _state = MutableStateFlow(TransactionAccountScreenState())
    val state = _state.asStateFlow()

    fun assemble(transactionType: TransactionType) {
        val accounts = when (transactionType) {
            TransactionType.INCOME -> accountRepository.getAllByType(type = AccountType.BankAccount)
            TransactionType.EXPENSE -> accountRepository.getAll()
        }

        _state.update {
            it.copy(
                accounts = accounts
            )
        }
    }
}