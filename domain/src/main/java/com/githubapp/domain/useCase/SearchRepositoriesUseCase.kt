package com.githubapp.domain.useCase

import com.githubapp.domain.model.Repository
import com.githubapp.domain.repository.SearchRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SearchRepositoriesUseCase @Inject internal constructor(private val searchRepository: SearchRepository) :
    UseCase<List<Repository>, SearchRepositoriesUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<List<Repository>> {
        return searchRepository.searchRepositories(params.q)
    }

    class Params(
        val q: String
    )

}