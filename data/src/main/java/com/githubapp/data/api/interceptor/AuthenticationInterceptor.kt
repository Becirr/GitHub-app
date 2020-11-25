package com.githubapp.data.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import kotlin.jvm.Throws

class AuthenticationInterceptor @Inject internal constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        original = original.newBuilder()
            .header("Accept", "application/json")
            .build()
        return chain.proceed(original)
    }
}