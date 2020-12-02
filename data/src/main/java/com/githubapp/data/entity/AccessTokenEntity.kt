package com.githubapp.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AccessTokenEntity(
    @SerializedName("access_token")
    var accessToken: String,
    @SerializedName("token_type")
    var tokenType: String
) : Serializable