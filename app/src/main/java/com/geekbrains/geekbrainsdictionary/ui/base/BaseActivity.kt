package com.geekbrains.geekbrainsdictionary.ui.base

import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.ui.viewmodel.BaseViewModel

/**
 * Базовая View. Часть функционала каждого экрана будет общей (например, создание презентера),
 * поэтому имеет смысл вывести его в родительский класс:*/

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    // В каждой Активити будет своя ViewModel, которая наследуется от BaseViewModel
    protected abstract val model: BaseViewModel<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // подписка на liveData
        model.getStateLiveData().observe(this) { renderData(it) }
    }

    // Каждая Активити будет отображать какие-то данные в соответствующем состоянии
    abstract fun renderData(appState: T)

}