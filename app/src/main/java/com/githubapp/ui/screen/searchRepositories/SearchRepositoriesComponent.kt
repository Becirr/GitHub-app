package com.githubapp.ui.screen.searchRepositories

import com.githubapp.di.scope.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [SearchRepositoriesModule::class])
interface SearchRepositoriesComponent {
    fun inject(searchRepositoriesActivity: SearchRepositoriesActivity): SearchRepositoriesActivity
}