package br.com.finius

import android.app.Application
import br.com.finius.features.bankAccounts.bankAccountsModule
import br.com.finius.features.cards.cardsModule
import br.com.finius.data.repository.repositoryModule
import br.com.finius.features.home.homeModule
import br.com.finius.features.transaction.newTransactionModule
import br.com.finius.features.transactionList.transactionListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FiniusApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FiniusApplication)
            modules(
                appModule,
                repositoryModule,
                homeModule,
                transactionListModule,
                bankAccountsModule,
                cardsModule,
                newTransactionModule,
            )
        }
    }
}