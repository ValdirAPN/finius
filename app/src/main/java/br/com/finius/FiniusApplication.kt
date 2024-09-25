package br.com.finius

import android.app.Application
import br.com.finius.bankAccounts.bankAccountsModule
import br.com.finius.cards.cardsModule
import br.com.finius.data.repository.repositoryModule
import br.com.finius.home.homeModule
import br.com.finius.transaction.newTransactionModule
import br.com.finius.transactionList.transactionListModule
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