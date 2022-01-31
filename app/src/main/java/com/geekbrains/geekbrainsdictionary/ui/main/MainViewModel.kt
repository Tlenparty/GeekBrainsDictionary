package com.geekbrains.geekbrainsdictionary.ui.main

import com.geekbrains.geekbrainsdictionary.interactor.main.MainInteractor
import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.Exception

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