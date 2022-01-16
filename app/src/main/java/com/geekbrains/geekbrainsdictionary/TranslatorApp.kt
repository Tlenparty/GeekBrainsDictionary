package com.geekbrains.geekbrainsdictionary

import android.app.Application
import com.geekbrains.geekbrainsdictionary.di.application
import com.geekbrains.geekbrainsdictionary.di.mainScreen
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}