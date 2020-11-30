package com.githubapp.data.datasource.remote

import com.githubapp.data.api.ApiService
import com.githubapp.data.datasource.UserDetailsDataSource
import com.githubapp.data.entity.OwnerEntity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class UserDetailsRemoteDataSource @Inject internal constructor(private val apiService: ApiService) :
    UserDetailsDataSource {

    override fun getUserDetails(username: String): Observable<OwnerEntity> {
        return apiService.getUserDetails(username)
    }

}