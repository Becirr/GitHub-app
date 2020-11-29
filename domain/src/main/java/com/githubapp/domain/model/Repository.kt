package com.githubapp.domain.model

import java.io.Serializable

data class Repository(
    var id: String,
    var fullName: String,
    var description: String?,
    var forks: Int,
    var openIssues: Int,
    var watchers: Int,
    var updatedAt: String,
    var owner: Owner?,
    var language: String?
) : Serializable