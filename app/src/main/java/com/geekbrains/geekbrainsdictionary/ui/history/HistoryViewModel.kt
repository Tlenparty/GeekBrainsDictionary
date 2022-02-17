package com.geekbrains.geekbrainsdictionary.ui.history

import android.util.Log
import androidx.lifecycle.LiveData
import com.geekbrains.geekbrainsdictionary.interactor.history.HistoryInteractor
import com.geekbrains.geekbrainsdictionary.interactor.history.IHistoryInteractor
import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.ui.base.BaseViewModel
import kotlinx.coroutines.*

class HistoryViewModel(
    private val interactor: IHistoryInteractor,
) : BaseViewModel<AppState>() {

    private val viewModelScope = CoroutineScope(
         Dispatchers.Main +
                SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            Log.e(HistoryViewModel::class.java.simpleName, "error", throwable)
        }
    )

    init {
         getData()
    }

    fun getData() {
        stateLiveData.value = AppState.Loading(null)
        viewModelScope.coroutineContext.cancelChildren()
        // Запускаем запрос данных из интерактора
        viewModelScope.launch {
            val data = interactor.getData()
            stateLiveData.value = data
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancelChildren()
    }
}