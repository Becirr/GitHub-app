package com.githubapp.data.datasource

import com.githubapp.data.entity.RepositoryEntity
import io.reactivex.rxjava3.core.Observable

interface RepositoryDetailsDataSource {
    fun getRepositoryDetails(username: String, repository: String): Observable<RepositoryEntity>
}