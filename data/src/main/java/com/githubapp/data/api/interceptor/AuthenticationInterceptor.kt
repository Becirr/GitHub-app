package com.githubapp.data.api.interceptor

import com.githubapp.data.cache.preferences.PreferencesManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import kotlin.jvm.Throws

class AuthenticationInterceptor @Inject internal constructor() : Interceptor {

    @Inject
    lateinit var preferencesManager: PreferencesManager

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val builder = original.newBuilder()
        if (preferencesManager.getAccessToken() != null) {
            builder.addHeader("Authorization", "token " + preferencesManager.getAccessToken())
        }
        original = builder
            .header("Accept", "application/json")
            .build()
        return chain.proceed(original)
    }
}