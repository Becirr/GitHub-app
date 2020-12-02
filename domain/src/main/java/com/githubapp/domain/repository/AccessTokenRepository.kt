package com.githubapp.domain.repository

import com.githubapp.domain.model.AccessToken
import io.reactivex.rxjava3.core.Observable

interface AccessTokenRepository {
    fun getAccessToken(url: String, clientId: String, clientSecret: String, code: String): Observable<AccessToken>
}