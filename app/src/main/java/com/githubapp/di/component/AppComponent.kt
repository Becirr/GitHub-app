package com.githubapp.di.component

import com.githubapp.data.api.ApiService
import com.githubapp.di.module.ApiModule
import com.githubapp.di.module.AppModule
import com.githubapp.di.module.HttpModule
import com.githubapp.domain.repository.AccessTokenRepository
import com.githubapp.domain.repository.RepositoryDetailsRepository
import com.githubapp.domain.repository.SearchRepository
import com.githubapp.domain.repository.UserDetailsRepository
import com.githubapp.ui.screen.login.LoginComponent
import com.githubapp.ui.screen.login.LoginModule
import com.githubapp.ui.screen.repositoryDetails.RepositoryDetailsComponent
import com.githubapp.ui.screen.repositoryDetails.RepositoryDetailsModule
import com.githubapp.ui.screen.searchRepositories.SearchRepositoriesComponent
import com.githubapp.ui.screen.searchRepositories.SearchRepositoriesModule
import com.githubapp.ui.screen.userDetails.UserDetailsComponent
import com.githubapp.ui.screen.userDetails.UserDetailsModule
import com.githubapp.ui.screen.webView.WebViewComponent
import com.githubapp.ui.screen.webView.WebViewModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, HttpModule::class, ApiModule::class])
interface AppComponent {

    fun apiService(): ApiService

    /**
     * Repositories
     */
    fun searchRepository(): SearchRepository
    fun userDetailsRepository(): UserDetailsRepository
    fun repositoryDetailsRepository(): RepositoryDetailsRepository
    fun accessTokenRepository(): AccessTokenRepository

    /**
     * SubComponents
     */
    fun plus(module: SearchRepositoriesModule): SearchRepositoriesComponent
    fun plus(module: UserDetailsModule): UserDetailsComponent
    fun plus(module: WebViewModule): WebViewComponent
    fun plus(module: RepositoryDetailsModule): RepositoryDetailsComponent
    fun plus(module: LoginModule): LoginComponent

}