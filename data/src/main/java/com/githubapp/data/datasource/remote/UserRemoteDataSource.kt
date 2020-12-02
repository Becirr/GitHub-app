package com.githubapp.data.datasource.remote

import com.githubapp.data.api.ApiService
import com.githubapp.data.datasource.UserDataSource
import com.githubapp.data.entity.OwnerEntity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class UserRemoteDataSource @Inject internal constructor(private val apiService: ApiService) :
    UserDataSource {

    override fun getUser(): Observable<OwnerEntity> {
        return apiService.getUser()
    }

}