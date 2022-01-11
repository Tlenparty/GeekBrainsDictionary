package com.geekbrains.geekbrainsdictionary.model.data

// Состояние одного экрана
sealed class AppState {
    // Успех. Количество слов из словаря List<Модель данных>
    data class Success(val data: List<DataModel>) : AppState()

    // Ошибка
    data class Error(val error: Throwable) : AppState()

    // Загрузка
    data class Loading(val progress: Int? = null) : AppState()

}
