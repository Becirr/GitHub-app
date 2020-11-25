package com.githubapp.domain.exception

/**
 * Interface to represent a wrapper around an [Exception] to manage errors
 */
interface Error {
    fun getException(): Exception
    fun getMessage(): String
}