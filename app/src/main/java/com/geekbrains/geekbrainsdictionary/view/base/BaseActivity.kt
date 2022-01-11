package com.geekbrains.geekbrainsdictionary.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.presenter.Presenter

/**
 * Базовая View. Часть функционала каждого экрана будет общей (например, создание презентера),
 * поэтому имеет смысл вывести его в родительский класс:*/

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    // Храним ссылку на презентер (AppState,View)
    protected lateinit var presenter: Presenter<T, View>

    // Создание презентера (Похоже на Moxy)
    protected abstract fun createPresenter(): Presenter<T, View>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Создаем presenter
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }

}