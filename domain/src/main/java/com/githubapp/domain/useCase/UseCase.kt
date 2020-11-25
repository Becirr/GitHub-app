package com.githubapp.domain.useCase

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class UseCase<T, Params> internal constructor() {
    private var disposables: CompositeDisposable = CompositeDisposable()

    /**
     * Builds an [Observable] which will be used when executing the current [UseCase]
     */
    abstract fun buildUseCaseObservable(params: Params): Observable<T>

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableObserver] which will be listening to the observable build
     * by [.buildUseCaseObservable] ()} method
     * @param params   Parameters (Optional) used to build/execute this use case
     */
    fun execute(observer: DisposableObserver<T>, params: Params) {
        val observable: Observable<T> = buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        subscribe(observable.subscribeWith(observer))
    }

    /**
     * Dispose from current [CompositeDisposable]
     */
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable]
     */
    private fun subscribe(disposable: Disposable) {
        disposables.add(disposable)
    }

}