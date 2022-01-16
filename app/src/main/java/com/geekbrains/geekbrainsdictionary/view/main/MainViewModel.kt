package com.geekbrains.geekbrainsdictionary.view.main

import androidx.lifecycle.LiveData
import com.geekbrains.geekbrainsdictionary.interactor.main.MainInteractor
import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.model.datasource.remote.DataSourceRemote
import com.geekbrains.geekbrainsdictionary.model.repository.RepositoryImplementation
import com.geekbrains.geekbrainsdictionary.presenter.Interactor
import com.geekbrains.geekbrainsdictionary.view.viewmodel.BaseViewModel
import io.reactivex.observers.DisposableObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MainViewModel(
    private val interactor: MainInteractor
) : BaseViewModel<AppState>() {

    // По дефолту запускаем корутину на Main потоке
    private val viewModelScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob()
    )

    // Когда вью подписывается на liveData запускем Rx-ое получение данных из интрактора
    // И подписываемся в getObserver()
    fun getWordDescriptions(word: String, isOnline: Boolean) {
        viewModelScope.launch {
            try {
                val data = interactor.getData(word, isOnline)
                stateLiveData.value = data
            } catch (e: Exception) {
                stateLiveData.value = AppState.Error(e)
            }
        }
    }

}