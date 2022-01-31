package com.geekbrains.geekbrainsdictionary.di

import com.geekbrains.geekbrainsdictionary.interactor.main.MainInteractor
import com.geekbrains.geekbrainsdictionary.model.data.DataModel
import com.geekbrains.geekbrainsdictionary.model.datasource.DataSource
import com.geekbrains.geekbrainsdictionary.model.datasource.remote.RetrofitImplementation
import com.geekbrains.geekbrainsdictionary.model.repository.Repository
import com.geekbrains.geekbrainsdictionary.model.repository.RepositoryImplementation
import com.geekbrains.geekbrainsdictionary.ui.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

// Для удобства создадим две переменные: в одной находятся зависимости,
// используемые во всём приложении, во второй - зависимости конкретного экрана
//module { } — создание модуля или субмодуля (модуля внутри модуля). Module очень похож на модули в Dagger: это контейнер для коллекции зависимостей;

val application = module {
    single<DataSource<List<DataModel>>>(named(NAME_REMOTE)) { RetrofitImplementation() }
    // генерация синглтона (созда 1 раз и будет жить во время жизни приложения )
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) { RepositoryImplementation(get(named(NAME_REMOTE))) }

    single<DataSource<List<DataModel>>>(named(NAME_LOCAL)) { RetrofitImplementation() } //TODO

    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) { RepositoryImplementation(get(named(
        NAME_LOCAL))) }
}

// Функция factory сообщает Koin, что эту зависимость нужно создавать каждый
// раз заново, что как раз подходит для Activity и её компонентов.
val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)),get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}