package com.githubapp

import android.app.Application
import android.content.Context
import com.githubapp.di.component.AppComponent
import com.githubapp.di.component.DaggerAppComponent
import com.githubapp.di.module.AppModule

class App : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        // Dagger 2
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        var context: Context? = null
    }
}