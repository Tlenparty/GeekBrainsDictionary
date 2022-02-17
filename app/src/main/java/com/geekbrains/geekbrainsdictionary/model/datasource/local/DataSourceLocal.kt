package com.geekbrains.geekbrainsdictionary.model.datasource.local

import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.model.datasource.DataSource

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveData(appState: AppState)
}