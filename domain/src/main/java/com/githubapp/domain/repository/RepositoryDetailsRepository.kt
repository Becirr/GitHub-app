package com.githubapp.domain.repository

import com.githubapp.domain.model.Repository
import io.reactivex.rxjava3.core.Observable

interface RepositoryDetailsRepository {
    fun getRepositoryDetails(username: String, repository: String): Observable<Repository>
}