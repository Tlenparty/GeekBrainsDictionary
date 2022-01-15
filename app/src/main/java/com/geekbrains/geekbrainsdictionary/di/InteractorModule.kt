package com.geekbrains.geekbrainsdictionary.di

import com.geekbrains.geekbrainsdictionary.di.NAME_LOCAL
import com.geekbrains.geekbrainsdictionary.di.NAME_REMOTE
import com.geekbrains.geekbrainsdictionary.interactor.main.MainInteractor
import com.geekbrains.geekbrainsdictionary.model.data.DataModel
import com.geekbrains.geekbrainsdictionary.model.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    fun provideInteractor(
        @Named(NAME_REMOTE) remoteRepo: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) localRepo: Repository<List<DataModel>>
    ) = MainInteractor(remoteRepo, localRepo)
}