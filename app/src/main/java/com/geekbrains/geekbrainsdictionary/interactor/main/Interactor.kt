package com.geekbrains.geekbrainsdictionary.interactor.main


// Слой для бизнес-логики
interface Interactor<T> {
    // Use Сase: получение данных для вывода на экран
    suspend fun getData(word:String, isRemoteSource:Boolean): T
}