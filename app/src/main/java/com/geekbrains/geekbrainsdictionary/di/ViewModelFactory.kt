package com.geekbrains.geekbrainsdictionary.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

// Фабрика по созданию ViewModel. Сюда будем закидывать все viewModel и через map будем их доставать
// ViewModelProvider.Factory строит ViewModel если в конструткор хотим что-то необычне передать
// MutableMap мы инджектим в конструктор с помощью мультибиндинга (когда получаем обьект конкретный по ключу)
@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {
    // По классу вью модели будет создавать вьюмодель
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = viewModels[modelClass]
        // Если в creator не будет viewModel
        return try {
            // Кастим viewModel к определенному типу Т
            creator?.get() as T
            // Ловим ошибку при касте
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}