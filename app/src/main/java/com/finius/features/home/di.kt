package com.finius.features.home

import com.finius.features.home.presentation.HomeScreenModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val homeDi = DI.Module("home-module") {
    bindProvider {
        HomeScreenModel(
            transactionRepository = instance()
        )
    }
}