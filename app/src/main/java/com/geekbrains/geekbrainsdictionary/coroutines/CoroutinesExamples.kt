package com.geekbrains.geekbrainsdictionary.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

/** Scope - скоупы в которых будут существовать корутины,
Context - контейнер в котором будут хранится и передаваться объекты для работы,
Job - еденица работы,
Suspend fun - перрывающаяся функция,
Dispatchers - механизм управления потоками,
 */

// Для асинхронной работы сосздадим job
var job: Job? = null

// Создание корутины начинается со scope (обычно передаем Dispatchers.IO (input, output сеть доступ к файлу (64 потока),
// Default (количество поток - количесвто ядер) Main (c главным потоком)
// Unconfined - гарантирует завершение в конкретном потоке
val scope = CoroutineScope(EmptyCoroutineContext)

fun main() {


    scope.launch {
        delay(2000)
        println("VVV DDD")
    }

    // Работает пока работает приложение его нельзя отменить.
    GlobalScope.launch {

    }

    // Запускется в главном потоке
    MainScope().launch {

    }

    fun onDestroy() {
        // Отмена scope
        scope.cancel()
    }


    // async await - builder, который что-то возвращает последней строчкой возвращает
    // CoroutineStart.LAZY - отложенный старт
    val jobAsync = scope.async(start = CoroutineStart.LAZY) {
        delay(2000)
        "abc"
    }

    scope.launch {
        jobAsync.await() // Поулчим строку
    }
}


private fun runSomeWork() {
    // is Active (любая joba уже active, isCanceled, isCompleted)
    job?.cancel()
    job = scope.launch {
        delay(2000)
        println("VVV DDD")
    }
}