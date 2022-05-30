package com.atahar.githubapi.di

import com.atahar.data.api.UserDetailsApi
import com.atahar.data.api.UserListApi
import com.atahar.data.repositories.UserRemoteDataSource
import com.atahar.data.repositories.UserRemoteDataSourceImpl
import com.atahar.data.repositories.UserRepoImpl
import com.atahar.domain.repositories.UserRepo
import com.atahar.domain.usecases.GetUserDetails
import com.atahar.domain.usecases.GetUserList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(
        userListApi: UserListApi,
        userDetailsApi: UserDetailsApi
    ): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(userListApi, userDetailsApi)
    }

    @Singleton
    @Provides
    fun provideUserRepo(
        userRemoteDataSource: UserRemoteDataSource
    ): UserRepo {
        return UserRepoImpl(userRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideGetUserDetails(userRepo: UserRepo) = GetUserDetails(userRepo)

    @Singleton
    @Provides
    fun provideGetUserList(userRepo: UserRepo) = GetUserList(userRepo)


}