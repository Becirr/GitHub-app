package com.githubapp.data.datasource

import com.githubapp.data.entity.RepositoryEntity
import io.reactivex.rxjava3.core.Observable

interface SearchDataSource {
    fun searchRepositories(q: String, sort: String?): Observable<List<RepositoryEntity>>
}