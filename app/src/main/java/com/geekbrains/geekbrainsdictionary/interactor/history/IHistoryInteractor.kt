package com.geekbrains.geekbrainsdictionary.interactor.history

import com.geekbrains.geekbrainsdictionary.model.data.AppState

interface IHistoryInteractor {
    suspend fun getData():AppState
}