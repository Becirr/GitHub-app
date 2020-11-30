package com.githubapp.domain.repository

import com.githubapp.domain.model.Owner
import io.reactivex.rxjava3.core.Observable

interface UserDetailsRepository {
    fun getUserDetails(username: String): Observable<Owner>
}