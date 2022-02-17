package com.geekbrains.geekbrainsdictionary.interactor.history

import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.model.data.DataModel
import com.geekbrains.geekbrainsdictionary.model.repository.local.RepositoryLocal

class HistoryInteractor(
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : IHistoryInteractor{

   /* override suspend fun getData(word: String, isRemoteSource: Boolean): AppState {
        val repo = if (isRemoteSource) {
            repositoryRemote
        } else {
            repositoryLocal
        }
        return AppState.Success(repo.getData(word))
    }*/

    override suspend fun getData(): AppState {
        return AppState.Success(repositoryLocal.getData(""))
    }
}