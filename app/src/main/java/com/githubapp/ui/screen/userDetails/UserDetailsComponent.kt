package com.githubapp.ui.screen.userDetails

import com.githubapp.di.scope.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [UserDetailsModule::class])
interface UserDetailsComponent {
    fun inject(userDetailsActivity: UserDetailsActivity): UserDetailsActivity
}