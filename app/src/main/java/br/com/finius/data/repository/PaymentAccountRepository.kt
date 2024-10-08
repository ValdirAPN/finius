package br.com.finius.data.repository

import br.com.finius.PaymentAccountEntityQueries
import br.com.finius.data.mapper.toPaymentAccount
import br.com.finius.domain.model.Money
import br.com.finius.domain.model.PaymentAccount
import br.com.finius.domain.model.PaymentAccountType

class PaymentAccountRepository(
    private val paymentAccountQueries: PaymentAccountEntityQueries
) {

    fun create(paymentAccount: PaymentAccount) = with(paymentAccount) {
        paymentAccountQueries.insert(
            id = id,
            name = name,
            balanceCents = balance.cents,
            type = type,
            dueDay = dueDay?.toLong(),
            closingDay = closingDay?.toLong(),
            color = color,
        )
    }

    fun getBalance() = Money(paymentAccountQueries.getBalance().executeAsOne().totalAmount ?: 0)

    private fun getAvailableLimit(id: String) = paymentAccountQueries.getAvailableLimit(id).executeAsOne().availableLimit ?: 0

    fun getAccountsByType(type: PaymentAccountType): List<PaymentAccount> {
        return paymentAccountQueries
            .listByType(type)
            .executeAsList()
            .map {
                val availableLimit = if (it.type == PaymentAccountType.CARD) Money(getAvailableLimit(it.id)) else null
                it.toPaymentAccount(availableLimit)
            }
    }
}

