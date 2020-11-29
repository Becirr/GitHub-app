package com.githubapp.ui.screen.searchRepositories

import com.githubapp.domain.model.Repository
import com.githubapp.ui.screen.base.MvpView

interface SearchRepositoriesView : MvpView {
    fun showRepositories(repositories: List<Repository>)
}