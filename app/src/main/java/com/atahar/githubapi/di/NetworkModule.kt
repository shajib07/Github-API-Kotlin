package com.atahar.githubapi.di

import com.atahar.data.api.UserDetailsApi
import com.atahar.data.api.UserListApi
import com.atahar.githubapi.utils.AppConfig.GITHUB_API_BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GITHUB_API_BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            ).build()
    }

    @Singleton
    @Provides
    fun provideUserListApi(): UserListApi =
        provideRetrofit().create(UserListApi::class.java)

    @Singleton
    @Provides
    fun provideUserDetailsApi(): UserDetailsApi =
        provideRetrofit().create(UserDetailsApi::class.java)


}