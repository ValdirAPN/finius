package br.com.finius.data.repository

import br.com.finius.PaymentAccountQueries
import br.com.finius.data.mapper.toPaymentAccount
import br.com.finius.domain.model.PaymentAccount
import br.com.finius.domain.model.PaymentAccountType

class PaymentAccountRepository(
    private val paymentAccountQueries: PaymentAccountQueries
) {

    fun create(paymentAccount: PaymentAccount) = with(paymentAccount) {
        paymentAccountQueries.insert(
            id = id,
            name = name,
            balanceCents = balance.cents,
            type = type,
            dueDay = dueDay?.toLong(),
            color = color,
        )
    }

    fun getAccountsByType(type: PaymentAccountType) =
        paymentAccountQueries
            .listByType(type)
            .executeAsList()
            .map { it.toPaymentAccount() }
}

