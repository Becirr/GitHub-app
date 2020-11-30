package com.githubapp.data.api

import com.githubapp.data.entity.OwnerEntity
import com.githubapp.data.entity.RepositoryEntity
import com.githubapp.data.entity.ResponseListDataEntity
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories")
    fun searchRepositories(@Query("q") q: String): Observable<ResponseListDataEntity<RepositoryEntity>>

    @GET("users/{username}")
    fun getUserDetails(@Path("username") username: String): Observable<OwnerEntity>

}