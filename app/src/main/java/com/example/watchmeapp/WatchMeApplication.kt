package com.example.watchmeapp

import android.app.Application
import com.example.watchmeapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WatchMeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WatchMeApplication)
            modules(appModule)
        }
    }
}