package com.geekbrains.geekbrainsdictionary.rx

import io.reactivex.Scheduler

interface ISchedulerProvider {

    val ui:Scheduler

    val io:Scheduler
}