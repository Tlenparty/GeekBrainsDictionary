package com.geekbrains.geekbrainsdictionary.di

import android.app.Application
import com.geekbrains.geekbrainsdictionary.TranslatorApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

// Тут мы прописываем все наши модули, включая AndroidnjectionModule. // Этот класс создаётся Dagger’ом. Он как раз связан с аннотацией ContributesAndroidInjector и позволяет внедрять в Activity все
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        InteractorModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        RepoModule::class
    ]
)

@Singleton
interface AppComponent {
    // Этот билдер мы вызовем из класса TranslatorApp, который наследует // Application
    @Component.Builder
    interface Builder {
        // Помогате прокидвывать классы, где они нуджы
        @BindsInstance
        fun application(app: Application): Builder

        fun builder(): AppComponent
    }

    fun inject(app: TranslatorApplication)

}

// необходимые зависимости
