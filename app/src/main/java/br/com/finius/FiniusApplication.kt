package br.com.finius

import android.app.Application
import br.com.finius.data.repository.repositoryModule
import br.com.finius.home.homeModule
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
                homeModule
            )
        }
    }
}