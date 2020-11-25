package com.githubapp.di.component

import com.githubapp.data.api.ApiService
import com.githubapp.di.module.ApiModule
import com.githubapp.di.module.AppModule
import com.githubapp.di.module.HttpModule
import javax.inject.Singleton
import dagger.Component

@Singleton
@Component(modules = [AppModule::class, HttpModule::class, ApiModule::class])
interface AppComponent {
    fun apiService(): ApiService
}