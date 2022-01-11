package com.geekbrains.geekbrainsdictionary.view.base

import com.geekbrains.geekbrainsdictionary.model.data.AppState

// Нижний уровень. View знает о контексте и фреймворке
interface View {
    // метод, в который приходит некое состояние приложения
    fun renderData(appState: AppState)
}