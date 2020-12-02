package com.githubapp.ui.screen.repositoryDetails

import com.githubapp.di.scope.PerActivity
import com.githubapp.domain.exception.ErrorMapper
import com.githubapp.domain.model.Owner
import com.githubapp.domain.model.Repository
import com.githubapp.domain.useCase.DefaultObserver
import com.githubapp.domain.useCase.RepositoryDetailsUseCase
import com.githubapp.domain.useCase.UserDetailsUseCase
import com.githubapp.ui.screen.base.BasePresenter
import javax.inject.Inject

@PerActivity
class RepositoryDetailsPresenter @Inject internal constructor(
    private val userDetailsUseCase: UserDetailsUseCase,
    private val repositoryDetailsUseCase: RepositoryDetailsUseCase,
) :
    BasePresenter<RepositoryDetailsView>() {

    override fun onAttach(view: RepositoryDetailsView) {
        super.onAttach(view)
        subscribe(userDetailsUseCase)
    }

    fun getUserDetails(username: String) {
        userDetailsUseCase.execute(object : DefaultObserver<Owner>() {
            override fun onNext(t: Owner) {
                view?.showUserDetails(t)
            }

            override fun onError(e: Throwable) {
                view?.showError(ErrorMapper(e as Exception))
            }
        }, UserDetailsUseCase.Params(username))
    }

    fun getRepositoryDetails(username: String, repository: String) {
        repositoryDetailsUseCase.execute(object : DefaultObserver<Repository>() {
            override fun onNext(t: Repository) {
                view?.showRepositoryDetails(t)
            }

            override fun onError(e: Throwable) {
                view?.showError(ErrorMapper(e as Exception))
            }
        }, RepositoryDetailsUseCase.Params(username, repository))
    }

}