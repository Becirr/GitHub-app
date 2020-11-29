package com.githubapp.ui.screen.base

import com.githubapp.domain.exception.Error

interface MvpView {
    fun showMessage(message: String)
    fun showError(error: Error)
}