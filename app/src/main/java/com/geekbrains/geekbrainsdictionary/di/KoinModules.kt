package com.geekbrains.geekbrainsdictionary.di

import androidx.room.Room
import com.geekbrains.geekbrainsdictionary.data.local.HistoryDatabase
import com.geekbrains.geekbrainsdictionary.data.local.LocalRepoImpl
import com.geekbrains.geekbrainsdictionary.data.local.RoomDataSource
import com.geekbrains.geekbrainsdictionary.interactor.main.MainInteractor
import com.geekbrains.geekbrainsdictionary.model.data.DataModel
import com.geekbrains.geekbrainsdictionary.model.datasource.DataSource
import com.geekbrains.geekbrainsdictionary.data.remote.RetrofitImplementation
import com.geekbrains.geekbrainsdictionary.model.repository.Repository
import com.geekbrains.geekbrainsdictionary.data.remote.RemoteRepoImpl
import com.geekbrains.geekbrainsdictionary.interactor.history.HistoryInteractor
import com.geekbrains.geekbrainsdictionary.interactor.history.IHistoryInteractor
import com.geekbrains.geekbrainsdictionary.model.datasource.local.DataSourceLocal
import com.geekbrains.geekbrainsdictionary.model.repository.local.RepositoryLocal
import com.geekbrains.geekbrainsdictionary.ui.history.HistoryViewModel
import com.geekbrains.geekbrainsdictionary.ui.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

// Для удобства создадим две переменные: в одной находятся зависимости,
// используемые во всём приложении, во второй - зависимости конкретного экрана
//module { } — создание модуля или субмодуля (модуля внутри модуля). Module очень похож на модули в Dagger: это контейнер для коллекции зависимостей;

val application = module {
    // Создадим бд
    single { Room.databaseBuilder(get(), HistoryDatabase::class.java, "HistoryDB").build() }
    // Напряму без обращения к бд запросить в дереве ссылочку на ДАО
    single { get<HistoryDatabase>().historyDao() }

    single<DataSource<List<DataModel>>>() { RetrofitImplementation() }
    single<Repository<List<DataModel>>>() { RemoteRepoImpl(get()) }

    single<DataSourceLocal<List<DataModel>>>() { RoomDataSource(get()) }
    single<RepositoryLocal<List<DataModel>>>() { LocalRepoImpl(get()) }
}

// Функция factory сообщает Koin, что эту зависимость нужно создавать каждый
// раз заново, что как раз подходит для Activity и её компонентов.
val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory<IHistoryInteractor> { HistoryInteractor(get()) }
    factory { HistoryViewModel(get()) }
}