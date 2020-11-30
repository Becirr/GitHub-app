package com.githubapp.ui.screen.userDetails

import com.githubapp.domain.model.Owner
import com.githubapp.ui.screen.base.MvpView

interface UserDetailsView : MvpView {
    fun showUser(owner: Owner)
}