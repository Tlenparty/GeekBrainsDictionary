package com.geekbrains.geekbrainsdictionary.data.remote

import com.geekbrains.geekbrainsdictionary.model.data.DataModel
import com.geekbrains.geekbrainsdictionary.model.datasource.DataSource
import com.geekbrains.geekbrainsdictionary.model.repository.Repository

class RemoteRepoImpl(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> = dataSource.getData(word)
}