package com.githubapp.domain.repository

import com.githubapp.domain.model.Repository
import io.reactivex.rxjava3.core.Observable

interface SearchRepository {
    fun searchRepositories(q: String, sort: String?): Observable<List<Repository>>
}