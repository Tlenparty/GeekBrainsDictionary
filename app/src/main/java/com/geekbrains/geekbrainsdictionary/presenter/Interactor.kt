package com.geekbrains.geekbrainsdictionary.presenter

import io.reactivex.Observable


// Слой для бизнес-логики
interface Interactor<T> {
    // Use Сase: получение данных для вывода на экран
    fun getData(word:String, isRemoteSource:Boolean): Observable<T>
}