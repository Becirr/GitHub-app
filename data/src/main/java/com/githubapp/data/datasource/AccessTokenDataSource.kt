package com.githubapp.data.datasource

import com.githubapp.data.entity.AccessTokenEntity
import io.reactivex.rxjava3.core.Observable

interface AccessTokenDataSource {
    fun getAccessToken(
        url: String,
        clientId: String,
        clientSecret: String,
        code: String
    ): Observable<AccessTokenEntity>
}