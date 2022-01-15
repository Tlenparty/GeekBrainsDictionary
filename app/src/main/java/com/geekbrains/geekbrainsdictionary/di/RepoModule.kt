package com.geekbrains.geekbrainsdictionary.di

import com.geekbrains.geekbrainsdictionary.model.data.DataModel
import com.geekbrains.geekbrainsdictionary.model.datasource.DataSource
import com.geekbrains.geekbrainsdictionary.model.datasource.remote.RetrofitImplementation
import com.geekbrains.geekbrainsdictionary.model.repository.Repository
import com.geekbrains.geekbrainsdictionary.model.repository.RepositoryImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepoModule {
    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRemoteRepo(@Named(NAME_REMOTE) dataSource: DataSource<List<DataModel>>): Repository<List<DataModel>> {
        return RepositoryImplementation(dataSource)
    }

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideLocalRepo(@Named(NAME_LOCAL) dataSource: DataSource<List<DataModel>>): Repository<List<DataModel>> {
        return RepositoryImplementation(dataSource)
    }

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRemoteDataSource(): DataSource<List<DataModel>> {
        return RetrofitImplementation()
    }

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideLocalDataSource(): DataSource<List<DataModel>> {
        return RetrofitImplementation() // todo
    }


}