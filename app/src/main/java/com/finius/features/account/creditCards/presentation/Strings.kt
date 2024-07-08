package com.finius.features.account.creditCards.presentation

import com.finius.features.account.creditCards.presentation.form.CardFormStrings
import com.finius.features.account.creditCards.presentation.form.CardFormStringsPt
import com.finius.features.account.creditCards.presentation.home.CreditCardsHomeStrings
import com.finius.features.account.creditCards.presentation.home.CreditCardsHomeStringsPt

data class CreditCardStrings(
    val homeStrings: CreditCardsHomeStrings,
    val formStrings: CardFormStrings,
    val successTitle: String
)

val CreditCardStringsPt = CreditCardStrings(
    homeStrings = CreditCardsHomeStringsPt,
    formStrings = CardFormStringsPt,
    successTitle = "Cartão criado com sucesso!"
)