package com.githubapp.domain.useCase

import com.githubapp.domain.model.AccessToken
import com.githubapp.domain.repository.AccessTokenRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class AccessTokenUseCase @Inject internal constructor(private val accessTokenRepository: AccessTokenRepository) :
    UseCase<AccessToken, AccessTokenUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<AccessToken> {
        return accessTokenRepository.getAccessToken(params.url, params.clientId,
            params.clientSecret,
            params.code)
    }

    class Params(
        val url: String,
        val clientId: String,
        val clientSecret: String,
        var code: String,
    )
}