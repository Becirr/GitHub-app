package com.githubapp.data.entity.mapper

import com.githubapp.data.entity.RepositoryEntity
import com.githubapp.domain.model.Repository
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class RepositoryMapper @Inject internal constructor(private val ownerMapper: OwnerMapper) {

    /**
     * Transform a [RepositoryEntity] into an [Repository].
     *
     * @param repositoryEntity Object to be transformed.
     * @return [Repository] if valid [RepositoryEntity] otherwise null.
     */
    fun transform(repositoryEntity: RepositoryEntity?): Repository? {
        if (repositoryEntity == null) {
            return null
        }
        return Repository(repositoryEntity.id,
            repositoryEntity.name,
            repositoryEntity.fullName,
            repositoryEntity.description,
            repositoryEntity.htmlUrl,
            repositoryEntity.forks,
            repositoryEntity.openIssues,
            repositoryEntity.watchers,
            repositoryEntity.updatedAt,
            repositoryEntity.createdAt,
            ownerMapper.transform(repositoryEntity.owner),
            repositoryEntity.language,
            repositoryEntity.subscribersCount)
    }

    /**
     * Transform a List of [RepositoryEntity] into a List of [Repository].
     *
     * @param repositoryEntityList Object List to be transformed.
     * @return [Repository] if valid [RepositoryEntity] otherwise null.
     */
    fun transform(repositoryEntityList: List<RepositoryEntity>?): List<Repository> {
        if (repositoryEntityList == null) {
            return ArrayList()
        }
        val repositoryList: ArrayList<Repository> = ArrayList()
        for (repositoryEntity in repositoryEntityList) {
            val repository: Repository? = transform(repositoryEntity)
            if (repository != null) {
                repositoryList.add(repository)
            }
        }
        return repositoryList
    }
}