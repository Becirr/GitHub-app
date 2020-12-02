package com.githubapp.data.datasource.remote

import com.githubapp.data.api.ApiService
import com.githubapp.data.datasource.SearchDataSource
import com.githubapp.data.entity.RepositoryEntity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SearchRemoteDataSource @Inject internal constructor(private val apiService: ApiService) :
    SearchDataSource {

    override fun searchRepositories(q: String, sort: String?): Observable<List<RepositoryEntity>> {
        return apiService.searchRepositories(q, sort).map { it.items }
    }

}