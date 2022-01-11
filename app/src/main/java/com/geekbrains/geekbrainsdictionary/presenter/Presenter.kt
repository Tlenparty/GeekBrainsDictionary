package com.geekbrains.geekbrainsdictionary.presenter

import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.view.base.View

// Презентер для View. Похож на наш Moxy
interface Presenter<T : AppState, V : View> {

    // К презентору присоеденяем View
    fun attachView(view: V)

    // Отсоединение View. Все из-за жизненного цикла View
    fun detachView(view: V)

    // Для управления View получение данных по некоторому слову в словаре + флаг из сети или нет данные
    fun getData(word: String, isOnline: Boolean)
}