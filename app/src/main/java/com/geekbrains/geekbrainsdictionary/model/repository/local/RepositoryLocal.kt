package com.geekbrains.geekbrainsdictionary.model.repository.local

import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.model.repository.Repository

interface RepositoryLocal<T>:Repository<T> {

    suspend fun saveData(appState: AppState)
}