package br.com.finius.data.repository

import br.com.finius.TransactionQueries
import br.com.finius.data.mapper.toTransaction
import br.com.finius.domain.model.Transaction

class TransactionRepository(
    private val transactionQueries: TransactionQueries
) {

    fun create(transaction: Transaction) = with (transaction) {
        transactionQueries.insert(
            id,
            name,
            amount,
            type,
            paymentAccountId,
            dateInMilli,
            installments.toLong(),
            party
        )
    }
    fun getTransactions() = transactionQueries.list().executeAsList().map { it.toTransaction() }
}