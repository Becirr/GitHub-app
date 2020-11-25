package com.githubapp.domain.exception

/**
 * Wrapper around Exceptions used to manage default errors
 *
 */
class ErrorMapper(private val exception: Exception) : Error {

    override fun getException(): Exception {
        return exception
    }

    override fun getMessage(): String {
        return exception.message ?: "Unknown error. Please contact support"
    }
}