package com.githubapp.data.repository

import com.githubapp.data.datasource.remote.RepositoryDetailsRemoteDataSource
import com.githubapp.data.entity.mapper.RepositoryMapper
import com.githubapp.domain.model.Repository
import com.githubapp.domain.repository.RepositoryDetailsRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryDetailsRepositoryImpl @Inject internal constructor(
    private val remoteDataSource: RepositoryDetailsRemoteDataSource,
    private val repositoryMapper: RepositoryMapper,
) : RepositoryDetailsRepository {

    override fun getRepositoryDetails(
        username: String,
        repository: String,
    ): Observable<Repository> {
        return remoteDataSource.getRepositoryDetails(username, repository)
            .map { repositoryMapper.transform(it) }
    }

}