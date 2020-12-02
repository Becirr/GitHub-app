package com.githubapp.domain.model

import java.io.Serializable

data class AccessToken(
    var accessToken: String,
    var tokenType: String
) : Serializable