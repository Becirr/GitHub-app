package com.githubapp.domain.model

import java.io.Serializable

data class Owner(
    var login: String,
    var avatarUrl: String,
    var htmlUrl: String,
    var blog: String?,
    var location: String?,
    var bio: String?,
    var name: String?,
    var publicRepos: Int?,
    var publicGists: Int?,
    var followers: Int?,
    var following: Int?,
    var hireable: Boolean?,
    var email: String?,
    var company: String?
) : Serializable