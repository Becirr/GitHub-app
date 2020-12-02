package com.githubapp.data.repository

import com.githubapp.data.datasource.remote.AccessTokenRemoteDataSource
import com.githubapp.data.entity.mapper.AccessTokenMapper
import com.githubapp.domain.model.AccessToken
import com.githubapp.domain.repository.AccessTokenRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccessTokenRepositoryImpl @Inject internal constructor(
    private val remoteDataSource: AccessTokenRemoteDataSource,
    private val accessTokenMapper: AccessTokenMapper,
) : AccessTokenRepository {

    override fun getAccessToken(
        url: String,
        clientId: String,
        clientSecret: String,
        code: String,
    ): Observable<AccessToken> {
        return remoteDataSource.getAccessToken(url, clientId, clientSecret, code)
            .map { accessTokenMapper.transform(it) }
    }

}