package com.geekbrains.geekbrainsdictionary.model.datasource.remote

import com.geekbrains.geekbrainsdictionary.model.data.DataModel
import com.geekbrains.geekbrainsdictionary.model.datasource.DataSource
import io.reactivex.Observable

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation()
) : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return remoteProvider.getData(word)
    }
}