package com.githubapp.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OwnerEntity (
    @SerializedName("avatar_url")
    var avatarUrl: String,
    @SerializedName("login")
    var login: String,
    @SerializedName("html_url")
    var htmlUrl: String,
    @SerializedName("blog")
    var blog: String?,
    @SerializedName("location")
    var location: String?,
    @SerializedName("bio")
    var bio: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("public_repos")
    var publicRepos: Int?,
    @SerializedName("public_gists")
    var publicGists: Int?,
    @SerializedName("followers")
    var followers: Int?,
    @SerializedName("following")
    var following: Int?,
    @SerializedName("hireable")
    var hireable: Boolean?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("company")
    var company: String?
) : Serializable