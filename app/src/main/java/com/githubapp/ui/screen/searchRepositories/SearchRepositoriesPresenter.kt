package com.githubapp.ui.screen.searchRepositories

import com.githubapp.Constants
import com.githubapp.di.scope.PerActivity
import com.githubapp.domain.exception.ErrorMapper
import com.githubapp.domain.model.AccessToken
import com.githubapp.domain.model.Owner
import com.githubapp.domain.model.Repository
import com.githubapp.domain.useCase.AccessTokenUseCase
import com.githubapp.domain.useCase.DefaultObserver
import com.githubapp.domain.useCase.SearchRepositoriesUseCase
import com.githubapp.domain.useCase.UserUseCase
import com.githubapp.ui.screen.base.BasePresenter
import javax.inject.Inject

@PerActivity
class SearchRepositoriesPresenter @Inject internal constructor(
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase,
    private val accessTokenUseCase: AccessTokenUseCase,
    private val userUseCase: UserUseCase
) :
    BasePresenter<SearchRepositoriesView>() {

    override fun onAttach(view: SearchRepositoriesView) {
        super.onAttach(view)
        subscribe(searchRepositoriesUseCase)
    }

    fun searchRepositories(q: String, sort: String?) {
        searchRepositoriesUseCase.execute(object : DefaultObserver<List<Repository>>() {
            override fun onNext(t: List<Repository>) {
                view?.showRepositories(t)
            }

            override fun onError(e: Throwable) {
                view?.showError(ErrorMapper(e as Exception))
            }
        }, SearchRepositoriesUseCase.Params(q, sort))
    }

    fun getAccessToken(clientId: String, clientSecret: String, code: String) {
        accessTokenUseCase.execute(object : DefaultObserver<AccessToken>() {
            override fun onNext(t: AccessToken) {
                view?.handleAccessToken(t)
            }

            override fun onError(e: Throwable) {
                view?.showError(ErrorMapper(e as Exception))
            }
        }, AccessTokenUseCase.Params(Constants.ACCESS_TOKEN_URL, clientId, clientSecret, code))
    }

    fun getUser() {
        userUseCase.execute(object : DefaultObserver<Owner>() {
            override fun onNext(t: Owner) {
                view?.showUser(t)
            }

            override fun onError(e: Throwable) {
                view?.showError(ErrorMapper(e as Exception))
            }
        }, Any())
    }

}