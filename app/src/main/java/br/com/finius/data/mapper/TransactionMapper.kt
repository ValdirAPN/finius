package br.com.finius.data.mapper

import br.com.finius.TransactionEntity
import br.com.finius.domain.model.Date
import br.com.finius.domain.model.Money
import br.com.finius.domain.model.Transaction

fun TransactionEntity.toTransaction() =
    Transaction(
        id = id,
        name = name,
        amount = Money(amountCents),
        type = type,
        paymentAccountId = paymentAccountId,
        date = Date(dateInMilli),
        installments = installments.toInt(),
        party = party,
        category = category
    )