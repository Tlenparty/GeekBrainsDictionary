package com.geekbrains.geekbrainsdictionary.data.local

import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.model.data.DataModel
import com.geekbrains.geekbrainsdictionary.model.datasource.DataSource
import com.geekbrains.geekbrainsdictionary.model.datasource.local.DataSourceLocal
import com.geekbrains.geekbrainsdictionary.model.repository.local.RepositoryLocal

class LocalRepoImpl(
    private val dataSource: DataSourceLocal<List<DataModel>>,
):RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveData(appState: AppState) {
        dataSource.saveData(appState)
    }
}