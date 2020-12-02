package com.githubapp.data.datasource

import com.githubapp.data.entity.OwnerEntity
import io.reactivex.rxjava3.core.Observable

interface UserDataSource {
    fun getUser(): Observable<OwnerEntity>
}