package br.com.finius.data.mapper

import br.com.finius.TransactionEntity
import br.com.finius.domain.model.Transaction

fun TransactionEntity.toTransaction() = Transaction(name, amount, type, color)