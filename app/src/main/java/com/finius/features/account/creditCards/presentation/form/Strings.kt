package com.finius.features.account.creditCards.presentation.form

import com.finius.features.account.creditCards.presentation.form.limit.CardLimitStrings
import com.finius.features.account.creditCards.presentation.form.limit.CardLimitStringsPt
import com.finius.features.account.creditCards.presentation.form.brand.CardBrandStrings
import com.finius.features.account.creditCards.presentation.form.brand.CardBrandStringsPt
import com.finius.features.account.creditCards.presentation.form.dueDay.CardDueDayStrings
import com.finius.features.account.creditCards.presentation.form.dueDay.CardDueDayStringsPt
import com.finius.features.account.creditCards.presentation.form.name.CardNameStrings
import com.finius.features.account.creditCards.presentation.form.name.CardNameStringsPt

data class CardFormStrings(
    val nameStrings: CardNameStrings,
    val brandStrings: CardBrandStrings,
    val limitStrings: CardLimitStrings,
    val dueDayStrings: CardDueDayStrings,
)

val CardFormStringsPt = CardFormStrings(
    nameStrings = CardNameStringsPt,
    brandStrings = CardBrandStringsPt,
    limitStrings = CardLimitStringsPt,
    dueDayStrings = CardDueDayStringsPt,
)