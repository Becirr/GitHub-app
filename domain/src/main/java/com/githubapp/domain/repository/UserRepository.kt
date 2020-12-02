package com.githubapp.domain.repository

import com.githubapp.domain.model.Owner
import io.reactivex.rxjava3.core.Observable

interface UserRepository {
    fun getUser(): Observable<Owner>
}