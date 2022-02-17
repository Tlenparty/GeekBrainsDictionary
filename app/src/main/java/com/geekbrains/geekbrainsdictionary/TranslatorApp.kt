package com.geekbrains.geekbrainsdictionary

import android.app.Application
import com.geekbrains.geekbrainsdictionary.di.application
import com.geekbrains.geekbrainsdictionary.di.historyScreen
import com.geekbrains.geekbrainsdictionary.di. mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}