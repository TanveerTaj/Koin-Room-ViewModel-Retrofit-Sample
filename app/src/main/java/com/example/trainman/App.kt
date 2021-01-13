package com.example.trainman

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(applicationContext)
            androidLogger(Level.DEBUG)
            modules(apiModule, viewModel, retrofitModule)
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}