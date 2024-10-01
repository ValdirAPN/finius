package br.com.finius.data.mapper

import br.com.finius.PaymentAccountEntity
import br.com.finius.domain.model.Money
import br.com.finius.domain.model.PaymentAccount

fun PaymentAccountEntity.toPaymentAccount() = PaymentAccount(
    id = id,
    name = name,
    balance = Money(balanceCents),
    type = type,
    dueDay = dueDay?.toInt(),
    closingDay = closingDay?.toInt(),
    color = color,
)