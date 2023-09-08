package br.com.maschdy.moviez

import android.app.Application
import br.com.maschdy.moviez.di.baseModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.loadKoinModules

class MovieZApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startDI()
    }

    private fun startDI() {
        startKoin {
            androidLogger()
            androidContext(this@MovieZApplication)
            loadKoinModules(baseModules)
        }
    }
}
