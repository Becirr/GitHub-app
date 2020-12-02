package com.githubapp.data.datasource.remote

import com.githubapp.data.api.ApiService
import com.githubapp.data.datasource.RepositoryDetailsDataSource
import com.githubapp.data.entity.RepositoryEntity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RepositoryDetailsRemoteDataSource @Inject internal constructor(private val apiService: ApiService) :
    RepositoryDetailsDataSource {

    override fun getRepositoryDetails(
        username: String,
        repository: String,
    ): Observable<RepositoryEntity> {
        return apiService.getRepository(username, repository)
    }

}