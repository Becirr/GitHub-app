package com.githubapp.domain.useCase

import com.githubapp.domain.model.Owner
import com.githubapp.domain.repository.UserDetailsRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class UserDetailsUseCase @Inject internal constructor(private val userDetailsRepository: UserDetailsRepository) :
    UseCase<Owner, UserDetailsUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<Owner> {
        return userDetailsRepository.getUserDetails(params.username)
    }

    class Params(
        val username: String
    )
}