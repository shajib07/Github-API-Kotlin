package com.atahar.githubapi.di

import com.atahar.data.api.RetrofitClient
import com.atahar.data.repositories.UserRemoteDataSourceImpl
import com.atahar.data.repositories.UserRepoImpl

object ServiceLocator {

/*
    @Volatile
    var userListRepo: UserRepoImpl? = null

    fun provideUserListRepo(): UserRepoImpl {
        synchronized(this) {
            return userListRepo ?: createUserRepo()
        }
    }

    private fun createUserRepo(): UserRepoImpl {
        val repo = UserRepoImpl(
            UserRemoteDataSourceImpl(
                RetrofitClient.userListApi,
                RetrofitClient.userDetailsApi
            )
        )

        userListRepo = repo
        return repo
    }
*/

}