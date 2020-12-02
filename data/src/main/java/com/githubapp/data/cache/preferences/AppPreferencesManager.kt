package com.githubapp.data.cache.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferencesManager @Inject internal constructor(application: Application) :
    PreferencesManager {
    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("githudemo.pref", Context.MODE_PRIVATE)

    companion object {
        private const val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
    }

    override fun setAccessToken(token: String) {
        sharedPreferences.edit().putString(PREF_KEY_ACCESS_TOKEN, token)
            .apply()
    }

    override fun getAccessToken(): String? {
        return sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, null)
    }
}
