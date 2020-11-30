package com.githubapp.data.datasource

import com.githubapp.data.entity.OwnerEntity
import io.reactivex.rxjava3.core.Observable

interface UserDetailsDataSource {
    fun getUserDetails(username: String): Observable<OwnerEntity>
}