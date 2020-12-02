package com.githubapp.data.repository

import com.githubapp.data.datasource.remote.UserRemoteDataSource
import com.githubapp.data.entity.mapper.OwnerMapper
import com.githubapp.domain.model.Owner
import com.githubapp.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject internal constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val ownerMapper: OwnerMapper
) : UserRepository {

    override fun getUser(): Observable<Owner> {
        return remoteDataSource.getUser().map { ownerMapper.transform(it) }
    }

}