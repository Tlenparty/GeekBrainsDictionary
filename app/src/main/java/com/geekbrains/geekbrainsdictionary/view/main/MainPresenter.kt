package com.geekbrains.geekbrainsdictionary.view.main

import android.annotation.SuppressLint
import com.geekbrains.geekbrainsdictionary.interactor.main.MainInteractor
import com.geekbrains.geekbrainsdictionary.model.data.AppState
import com.geekbrains.geekbrainsdictionary.model.datasource.remote.DataSourceRemote
import com.geekbrains.geekbrainsdictionary.model.repository.RepositoryImplementation
import com.geekbrains.geekbrainsdictionary.presenter.Interactor
import com.geekbrains.geekbrainsdictionary.presenter.Presenter
import com.geekbrains.geekbrainsdictionary.view.base.View
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter<T : AppState, V : View>(
    // Обратите внимание, что Интерактор мы создаём сразу в конструкторе
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceRemote())
    )
) : Presenter<T, V> {

    private val compositeDisposable = CompositeDisposable()

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (currentView != view) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        // Очищаем всем подписки
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(interactor.getData(word, isOnline)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
            .subscribe { currentView?.renderData(it) }
        )
    }
}