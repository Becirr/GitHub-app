package com.githubapp.domain.model

import java.io.Serializable

data class Repository(
    var id: String,
    var name: String,
    var fullName: String,
    var description: String?,
    var htmlUrl: String?,
    var forks: Int,
    var openIssues: Int,
    var watchers: Int,
    var updatedAt: String,
    var createdAt: String,
    var owner: Owner?,
    var language: String?,
    var subscribersCount: Int?
) : Serializable