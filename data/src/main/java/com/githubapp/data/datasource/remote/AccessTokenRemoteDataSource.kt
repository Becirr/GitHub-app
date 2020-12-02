package com.githubapp.data.datasource.remote

import com.githubapp.data.api.ApiService
import com.githubapp.data.datasource.AccessTokenDataSource
import com.githubapp.data.entity.AccessTokenEntity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class AccessTokenRemoteDataSource @Inject internal constructor(private val apiService: ApiService) :
    AccessTokenDataSource {

    override fun getAccessToken(
        url: String,
        clientId: String,
        clientSecret: String,
        code: String,
    ): Observable<AccessTokenEntity> {
        return apiService.getAccessToken(url, clientId, clientSecret, code)
    }

}