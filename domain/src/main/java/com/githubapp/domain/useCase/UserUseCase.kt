package com.githubapp.domain.useCase

import com.githubapp.domain.model.Owner
import com.githubapp.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class UserUseCase @Inject internal constructor(private val userRepository: UserRepository) :
    UseCase<Owner, Any>() {

    override fun buildUseCaseObservable(params: Any): Observable<Owner> {
        return userRepository.getUser()
    }

}