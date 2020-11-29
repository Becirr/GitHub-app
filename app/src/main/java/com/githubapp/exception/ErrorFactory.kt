package com.githubapp.exception

import com.githubapp.R
import com.githubapp.util.ResourceUtil
import com.google.gson.stream.MalformedJsonException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorFactory @Inject internal constructor(private val resourceUtil: ResourceUtil) {

    /**
     * Creates a String representing an onError message.
     *
     * @param exception An exception used as a condition to retrieve the correct onError message
     */
    fun create(exception: Exception): String {
        exception.printStackTrace()
        return when (exception) {
            is SocketTimeoutException -> {
                resourceUtil.getString(R.string.error_timeout)
            }
            is MalformedJsonException -> {
                resourceUtil.getString(R.string.error_malformed_json)
            }
            is IOException -> {
                resourceUtil.getString(R.string.error_no_internet_connection)
            }
            is HttpException -> {
                exception.response()!!.message()
            }
            else -> {
                resourceUtil.getString(R.string.error_unknown)
            }
        }
    }

}