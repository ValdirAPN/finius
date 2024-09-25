package br.com.finius.data.repository

import br.com.finius.TransactionEntityQueries
import br.com.finius.data.mapper.toTransaction
import br.com.finius.domain.model.Transaction

class TransactionRepository(
    private val transactionQueries: TransactionEntityQueries
) {

    fun create(transaction: Transaction) = with (transaction) {
        transactionQueries.insert(
            id,
            name,
            amount.cents,
            type,
            paymentAccountId,
            dateInMilli,
            installments.toLong(),
            party
        )
    }
    fun getTransactions() = transactionQueries.list().executeAsList().map { it.toTransaction() }
}