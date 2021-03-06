package com.githubapp.data.api

import com.githubapp.data.entity.AccessTokenEntity
import com.githubapp.data.entity.OwnerEntity
import com.githubapp.data.entity.RepositoryEntity
import com.githubapp.data.entity.ResponseListDataEntity
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface ApiService {

    @Headers("Accept: application/json")
    @POST
    @FormUrlEncoded
    fun getAccessToken(
        @Url url: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
    ): Observable<AccessTokenEntity>

    @GET("search/repositories")
    fun searchRepositories(
        @Query("q") q: String,
        @Query("sort") sort: String?,
    ): Observable<ResponseListDataEntity<RepositoryEntity>>

    @GET("users/{username}")
    fun getUserDetails(@Path("username") username: String): Observable<OwnerEntity>

    @GET("repos/{username}/{repository}")
    fun getRepository(
        @Path("username") username: String,
        @Path("repository") repository: String,
    ): Observable<RepositoryEntity>

    @GET("user")
    fun getUser(): Observable<OwnerEntity>

}