package com.githubapp.data.repository

import com.githubapp.data.datasource.remote.SearchRemoteDataSource
import com.githubapp.data.entity.mapper.RepositoryMapper
import com.githubapp.domain.model.Repository
import com.githubapp.domain.repository.SearchRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject internal constructor(
    private val remoteDataSource: SearchRemoteDataSource,
    private val repositoryMapper: RepositoryMapper,
) : SearchRepository {

    override fun searchRepositories(q: String, sort: String?): Observable<List<Repository>> {
        return remoteDataSource.searchRepositories(q, sort).map { repositoryMapper.transform(it) }
    }

}