package com.githubapp.ui.screen.base

import com.githubapp.domain.useCase.UseCase
import java.util.*

abstract class BasePresenter<V : MvpView> protected constructor() : MvpPresenter<V> {

    private val subscriptions: ArrayList<UseCase<*, *>> = ArrayList()
    protected var view: V? = null

    protected fun subscribe(useCase: UseCase<*, *>) {
        subscriptions.add(useCase)
    }

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun onDetach() {
        for (useCase in subscriptions) {
            useCase.dispose()
        }
        view = null
    }

}