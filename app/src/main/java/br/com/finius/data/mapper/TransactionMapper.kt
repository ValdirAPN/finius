package br.com.finius.data.mapper

import br.com.finius.FinancialTransaction
import br.com.finius.domain.model.Transaction

fun FinancialTransaction.toTransaction() = Transaction(name, amount, type, color)