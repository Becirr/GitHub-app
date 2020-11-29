package com.githubapp.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OwnerEntity (
    @SerializedName("avatar_url")
    var avatarUrl: String
) : Serializable