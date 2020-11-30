package com.githubapp.ui.screen.userDetails

import com.githubapp.di.scope.PerActivity
import com.githubapp.domain.exception.ErrorMapper
import com.githubapp.domain.model.Owner
import com.githubapp.domain.useCase.DefaultObserver
import com.githubapp.domain.useCase.UserDetailsUseCase
import com.githubapp.ui.screen.base.BasePresenter
import javax.inject.Inject

@PerActivity
class UserDetailsPresenter @Inject internal constructor(private val userDetailsUseCase: UserDetailsUseCase) :
    BasePresenter<UserDetailsView>() {

    override fun onAttach(view: UserDetailsView) {
        super.onAttach(view)
        subscribe(userDetailsUseCase)
    }

    fun getUserDetails(username: String) {
        userDetailsUseCase.execute(object : DefaultObserver<Owner>() {
            override fun onNext(t: Owner) {
                view?.showUser(t)
            }

            override fun onError(e: Throwable) {
                view?.showError(ErrorMapper(e as Exception))
            }
        }, UserDetailsUseCase.Params(username))
    }

}