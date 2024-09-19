package br.com.finius.data.repository

import br.com.finius.PaymentAccountQueries
import br.com.finius.data.mapper.toPaymentAccount
import br.com.finius.domain.model.PaymentAccountType

class PaymentAccountRepository(
    private val paymentAccountQueries: PaymentAccountQueries
) {

    fun getAccountsByType(type: PaymentAccountType) =
        paymentAccountQueries
            .listByType(type)
            .executeAsList()
            .map { it.toPaymentAccount() }
}