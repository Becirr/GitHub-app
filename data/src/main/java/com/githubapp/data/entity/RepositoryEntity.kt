package com.githubapp.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepositoryEntity(
    @SerializedName("id")
    var id: String,
    @SerializedName("full_name")
    var fullName: String,
    @SerializedName("description")
    var description: String?,
    @SerializedName("forks")
    var forks: Int,
    @SerializedName("open_issues")
    var openIssues: Int,
    @SerializedName("watchers")
    var watchers: Int,
    @SerializedName("updated_at")
    var updatedAt: String,
    @SerializedName("language")
    var language: String?,
    @SerializedName("owner")
    var owner: OwnerEntity?
) : Serializable