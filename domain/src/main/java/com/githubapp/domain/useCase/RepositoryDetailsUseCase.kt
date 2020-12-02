package com.githubapp.domain.useCase

import com.githubapp.domain.model.Repository
import com.githubapp.domain.repository.RepositoryDetailsRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RepositoryDetailsUseCase @Inject internal constructor(private val repositoryDetailsRepository: RepositoryDetailsRepository) :
    UseCase<Repository, RepositoryDetailsUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<Repository> {
        return repositoryDetailsRepository.getRepositoryDetails(params.username, params.repository)
    }

    class Params(
        val username: String,
        val repository: String,
    )
}