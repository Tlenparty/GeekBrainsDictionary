package com.geekbrains.geekbrainsdictionary.di

import com.geekbrains.geekbrainsdictionary.view.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*Так как мы используем дополнительную библиотеку поддержки для Android, то все становится гораздо проще при помощи ContributesAndroidInjector. Он позволяет внедрять зависимости в Activity (нашу ViewModel) благодаря простому AndroidInjection.inject(this) в методе onCreate.*/

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}