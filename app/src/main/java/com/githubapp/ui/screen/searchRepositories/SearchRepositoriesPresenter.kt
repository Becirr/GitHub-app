package com.githubapp.ui.screen.searchRepositories

import com.githubapp.di.scope.PerActivity
import com.githubapp.domain.exception.ErrorMapper
import com.githubapp.domain.model.Repository
import com.githubapp.domain.useCase.DefaultObserver
import com.githubapp.domain.useCase.SearchRepositoriesUseCase
import com.githubapp.ui.screen.base.BasePresenter
import javax.inject.Inject

@PerActivity
class SearchRepositoriesPresenter @Inject internal constructor(private val searchRepositoriesUseCase: SearchRepositoriesUseCase) :
    BasePresenter<SearchRepositoriesView>() {

    override fun onAttach(view: SearchRepositoriesView) {
        super.onAttach(view)
        subscribe(searchRepositoriesUseCase)
    }

    fun searchRepositories(q: String) {
        searchRepositoriesUseCase.execute(object : DefaultObserver<List<Repository>>() {
            override fun onNext(t: List<Repository>) {
                view?.showRepositories(t)
            }

            override fun onError(e: Throwable) {
                view?.showError(ErrorMapper(e as Exception))
            }
        }, SearchRepositoriesUseCase.Params(q))
    }

}