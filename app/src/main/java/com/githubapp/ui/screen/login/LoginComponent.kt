package com.githubapp.ui.screen.login

import com.githubapp.di.scope.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {
    fun inject(loginActivity: LoginActivity): LoginActivity
}