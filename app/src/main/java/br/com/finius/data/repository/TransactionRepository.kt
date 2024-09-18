package br.com.finius.data.repository

import br.com.finius.TransactionQueries
import br.com.finius.data.mapper.toTransaction

class TransactionRepository(
    private val transactionQueries: TransactionQueries
) {

    fun getTransactions() = transactionQueries.list().executeAsList().map { it.toTransaction() }
}