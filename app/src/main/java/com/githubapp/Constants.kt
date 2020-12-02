package com.githubapp

object Constants {
    const val BASE_URL: String = "https://api.github.com/"
    const val CLIENT_ID: String = "786ed62d30dde4b702a0"
    const val CLIENT_SECRET: String = "aabfe7c8c40bab477dde7703d3d574dda14433ce"
    const val AUTHORIZE_URL: String = "https://github.com/login/oauth/authorize?client_id=$CLIENT_ID"
    const val ACCESS_TOKEN_URL: String = "https://github.com/login/oauth/access_token"
    const val REDIRECT_URL: String = "githubdemoapp://callback"
}