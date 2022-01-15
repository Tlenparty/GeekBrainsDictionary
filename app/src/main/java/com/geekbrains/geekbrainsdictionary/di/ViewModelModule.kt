package com.geekbrains.geekbrainsdictionary.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.geekbrainsdictionary.view.main.MainViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass


@Module(includes = [InteractorModule::class])
internal abstract class ViewModelModule {

    // Фабрика для получения ViewModelFactory
    @Binds
    internal abstract fun bindViewModelProviderFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    // Этот метод просто говорит Dagger’у: помести эту модель в список (map) моделей,
    // используя аннотацию @IntoMap, где в качестве ключа будет класс MainViewModel::class
    @Binds
    @IntoMap // Мультибиндинг для
    @ViewModelKey(MainViewModel::class)
    protected abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel
}
// Аннотация для прокидывания по ключу (класс ViewModel) закидывать провайдеры вью моделей
// Создадим ключ, по которому будем хранить нашу ViewModel в фабрике:
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
// Говорит о том что мы где-то хотим создать map с ключом ViewModelKey
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

