package com.geekbrains.geekbrainsdictionary

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

// HasAndroidInjector: мы переопределяем его метод androidInjector.  и dispatcherAndroidInjectorОни
// нужны для внедрения зависимостей в Activity. По своей сути — это вспомогательные методы для разработчиков под Андроид для эффективного внедрения компонентов платформы, таких как Активити, Сервис и т. п.

class TranslatorApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
      /*  DaggerAppComponent.builder() DaggerAppCompat не рабоатет =(
            .application(this)
            .build()
            .inject(this)*/
    }
}