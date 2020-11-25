package com.githubapp.domain.useCase

class BaseParams(private var loadRemote: Boolean) {

    fun shouldLoadRemote(): Boolean {
        return loadRemote
    }

    fun setLoadRemote(loadRemote: Boolean) {
        this.loadRemote = loadRemote
    }

}