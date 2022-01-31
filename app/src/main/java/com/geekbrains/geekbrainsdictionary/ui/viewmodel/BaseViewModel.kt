package com.geekbrains.geekbrainsdictionary.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.rx.ISchedulerProvider
import com.geekbrains.geekbrainsdictionary.rx.SchedulerProvider

abstract class BaseViewModel<T : AppState>(
    protected val stateLiveData: MutableLiveData<T> = MutableLiveData(),
    protected val schedulerProvider: ISchedulerProvider = SchedulerProvider()
) : ViewModel() {

    fun getStateLiveData(): LiveData<T> = stateLiveData

    override fun onCleared() {
    }

}