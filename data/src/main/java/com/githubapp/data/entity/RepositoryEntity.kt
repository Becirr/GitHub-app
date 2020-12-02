package com.githubapp.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepositoryEntity(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("full_name")
    var fullName: String,
    @SerializedName("description")
    var description: String?,
    @SerializedName("html_url")
    var htmlUrl: String?,
    @SerializedName("forks")
    var forks: Int,
    @SerializedName("open_issues")
    var openIssues: Int,
    @SerializedName("watchers")
    var watchers: Int,
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("updated_at")
    var updatedAt: String,
    @SerializedName("language")
    var language: String?,
    @SerializedName("owner")
    var owner: OwnerEntity?,
    @SerializedName("subscribers_count")
    var subscribersCount: Int?
) : Serializable