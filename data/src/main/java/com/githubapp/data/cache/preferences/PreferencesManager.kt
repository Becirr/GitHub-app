package com.githubapp.data.cache.preferences

interface PreferencesManager {
    fun setAccessToken(token: String)
    fun getAccessToken(): String?
}