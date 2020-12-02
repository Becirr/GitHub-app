package com.githubapp.data.entity.mapper

import com.githubapp.data.entity.AccessTokenEntity
import com.githubapp.domain.model.AccessToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccessTokenMapper @Inject internal constructor() {

    /**
     * Transform a [AccessTokenEntity] into an [AccessToken].
     *
     * @param accessTokenEntity Object to be transformed.
     * @return [AccessToken] if valid [AccessTokenEntity] otherwise null.
     */
    fun transform(accessTokenEntity: AccessTokenEntity?): AccessToken? {
        if (accessTokenEntity == null) {
            return null
        }
        return AccessToken(accessTokenEntity.accessToken,
            accessTokenEntity.tokenType)
    }

}