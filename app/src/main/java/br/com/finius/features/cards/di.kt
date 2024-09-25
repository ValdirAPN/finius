package br.com.finius.features.cards

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val cardsModule = module {
    viewModelOf(::CardsViewModel)
    viewModelOf(::NewCardViewModel)
}