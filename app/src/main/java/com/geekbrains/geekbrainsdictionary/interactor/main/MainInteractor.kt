package com.geekbrains.geekbrainsdictionary.interactor.main

import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.model.data.DataModel
import com.geekbrains.geekbrainsdictionary.model.repository.Repository
import com.geekbrains.geekbrainsdictionary.presenter.Interactor
import io.reactivex.Observable
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {
    // Интерактор лишь запрашивает у репозитория данные, детали имплементации ему не известны
    override fun getData(word: String, isRemoteSource: Boolean): Observable<AppState> {
        return if (isRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}