package com.githubapp.ui.screen.repositoryDetails

import com.githubapp.domain.model.Owner
import com.githubapp.domain.model.Repository
import com.githubapp.ui.screen.base.MvpView

interface RepositoryDetailsView : MvpView {
    fun showUserDetails(owner: Owner)
    fun showRepositoryDetails(repository: Repository)
}