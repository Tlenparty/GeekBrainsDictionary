package com.geekbrains.geekbrainsdictionary.view.main

import androidx.lifecycle.LiveData
import com.geekbrains.geekbrainsdictionary.interactor.main.MainInteractor
import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.model.datasource.remote.DataSourceRemote
import com.geekbrains.geekbrainsdictionary.model.repository.RepositoryImplementation
import com.geekbrains.geekbrainsdictionary.presenter.Interactor
import com.geekbrains.geekbrainsdictionary.view.viewmodel.BaseViewModel
import io.reactivex.observers.DisposableObserver

class MainViewModel(
    private val interactor: Interactor<AppState> = MainInteractor(
        remoteRepository = RepositoryImplementation(DataSourceRemote()),
        localRepository = RepositoryImplementation(DataSourceRemote())
    )
) : BaseViewModel<AppState>() {

    // Когда вью подписывается на liveData запускем Rx-ое получение данных из интрактора
    // И подписываемся в getObserver()
    fun getWordDescriptions(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io)
                .observeOn(schedulerProvider.ui)
                .doOnSubscribe {stateLiveData.value = AppState.Loading(null)} // Выполняется в самом начале
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver() = object : DisposableObserver<AppState>(){
        // Данные успешно загружены; сохраняем их и передаем во View (через
        // LiveData). View сама разберётся, как их отображать
        override fun onNext(appState: AppState) {
            stateLiveData.value = appState
        }
        // В случае ошибки передаём её в Activity таким же образом через LiveData
        override fun onError(e: Throwable) {
            stateLiveData.value = AppState.Error(e)
        }

        override fun onComplete() = Unit

    }

}