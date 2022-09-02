package com.example.retrofitroompaging3sampleproject.di

import com.example.retrofitroompaging3sampleproject.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofitClient(baseUrl: String, gsonConverter: GsonConverterFactory): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(gsonConverter).build()

    @Provides
    fun provideBaseUrl(): String = "https://api.github.com"

    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()
}