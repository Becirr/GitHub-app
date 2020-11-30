package com.githubapp.data.entity.mapper

import com.githubapp.data.entity.OwnerEntity
import com.githubapp.domain.model.Owner
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OwnerMapper @Inject internal constructor() {

    /**
     * Transform a [OwnerEntity] into an [Owner].
     *
     * @param ownerEntity Object to be transformed.
     * @return [Owner] if valid [OwnerEntity] otherwise null.
     */
    fun transform(ownerEntity: OwnerEntity?): Owner? {
        if (ownerEntity == null) {
            return null
        }
        return Owner(ownerEntity.login,
            ownerEntity.avatarUrl,
            ownerEntity.htmlUrl,
            ownerEntity.blog,
            ownerEntity.location,
            ownerEntity.bio,
            ownerEntity.name,
            ownerEntity.publicRepos,
            ownerEntity.publicGists,
            ownerEntity.followers,
            ownerEntity.following,
            ownerEntity.hireable,
            ownerEntity.email,
            ownerEntity.company)
    }

    /**
     * Transform a List of [OwnerEntity] into a List of [Owner].
     *
     * @param ownerEntityList Object List to be transformed.
     * @return [Owner] if valid [OwnerEntity] otherwise null.
     */
    fun transform(ownerEntityList: List<OwnerEntity>?): List<Owner> {
        if (ownerEntityList == null) {
            return ArrayList()
        }
        val ownerList: ArrayList<Owner> = ArrayList()
        for (ownerEntity in ownerEntityList) {
            val owner: Owner? = transform(ownerEntity)
            if (owner != null) {
                ownerList.add(owner)
            }
        }
        return ownerList
    }

}