package com.githubapp.domain.useCase

import io.reactivex.rxjava3.observers.DisposableObserver

abstract class DefaultObserver<T> : DisposableObserver<T>() {
    override fun onNext(t: T) {}
    override fun onError(e: Throwable) {}
    override fun onComplete() {}
}