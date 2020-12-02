package com.githubapp.ui.screen.repositoryDetails

import com.githubapp.di.scope.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [RepositoryDetailsModule::class])
interface RepositoryDetailsComponent {
    fun inject(repositoryDetailsActivity: RepositoryDetailsActivity): RepositoryDetailsActivity
}