package com.githubapp.di.module

import com.githubapp.Constants
import com.githubapp.data.api.ApiService
import com.githubapp.data.repository.SearchRepositoryImpl
import com.githubapp.data.repository.UserDetailsRepositoryImpl
import com.githubapp.domain.repository.SearchRepository
import com.githubapp.domain.repository.UserDetailsRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    @Named("ApiClient")
    fun provideRetrofitClient(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(@Named("ApiClient") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    /**
     * Repository
     */
    @Provides
    @Singleton
    fun provideSearchRepository(searchRepository: SearchRepositoryImpl): SearchRepository {
        return searchRepository
    }

    @Provides
    @Singleton
    fun provideUserDetailsRepository(userDetailsRepository: UserDetailsRepositoryImpl): UserDetailsRepository {
        return userDetailsRepository
    }

}