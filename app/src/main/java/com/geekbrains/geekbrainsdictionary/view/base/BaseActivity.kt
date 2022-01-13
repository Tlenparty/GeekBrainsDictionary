package com.geekbrains.geekbrainsdictionary.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.presenter.Presenter
import com.geekbrains.geekbrainsdictionary.view.viewmodel.BaseViewModel

/**
 * Базовая View. Часть функционала каждого экрана будет общей (например, создание презентера),
 * поэтому имеет смысл вывести его в родительский класс:*/

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // подписка на liveData
        model.getStateLiveData().observe(this) { renderData(it) }
    }

    // В каждой Активити будет своя ViewModel, которая наследуется от BaseViewModel
    abstract val model: BaseViewModel<T>

    // Каждая Активити будет отображать какие-то данные в соответствующем состоянии
    abstract fun renderData(appState: T)

}