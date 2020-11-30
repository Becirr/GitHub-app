package com.githubapp.data.repository

import com.githubapp.data.datasource.remote.UserDetailsRemoteDataSource
import com.githubapp.data.entity.mapper.OwnerMapper
import com.githubapp.domain.model.Owner
import com.githubapp.domain.repository.UserDetailsRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDetailsRepositoryImpl @Inject internal constructor(
    private val remoteDataSource: UserDetailsRemoteDataSource,
    private val ownerMapper: OwnerMapper,
) : UserDetailsRepository {

    override fun getUserDetails(username: String): Observable<Owner> {
        return remoteDataSource.getUserDetails(username).map { ownerMapper.transform(it) }
    }

}