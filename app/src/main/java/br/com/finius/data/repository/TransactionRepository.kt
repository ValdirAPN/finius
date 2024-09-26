package br.com.finius.data.repository

import br.com.finius.TransactionEntityQueries
import br.com.finius.data.mapper.toTransaction
import br.com.finius.domain.model.Transaction

class TransactionRepository(
    private val transactionQueries: TransactionEntityQueries
) {

    fun create(transaction: Transaction) = with (transaction) {
        transactionQueries.insert(
            id = id,
            name = name,
            amountCents = amount.cents,
            type = type,
            paymentAccountId = paymentAccountId,
            dateInMilli = date.millis,
            installments = installments.toLong(),
            party = party,
            category = category
        )
    }

    fun list() = transactionQueries.list().executeAsList().map { it.toTransaction() }

    fun listNewest() = transactionQueries.listNewest().executeAsList().map { it.toTransaction() }
}