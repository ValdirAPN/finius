package com.finius

import android.app.Application
import org.kodein.di.DI
import org.kodein.di.DIAware

class FiniusApplication : Application(), DIAware {

    override val di by DI.lazy {}
}