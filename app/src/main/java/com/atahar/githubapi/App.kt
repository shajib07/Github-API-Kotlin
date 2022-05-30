package com.atahar.githubapi

import android.app.Application
import com.atahar.data.repositories.UserRepoImpl
import com.atahar.domain.usecases.GetUserDetails
import com.atahar.domain.usecases.GetUserList
import com.atahar.githubapi.di.ServiceLocator
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

/*
    private val userListRepo: UserRepoImpl
        get() = ServiceLocator.provideUserListRepo()

    val getUserList: GetUserList
        get() = GetUserList(userListRepo)

    val getUserDetails: GetUserDetails
        get() = GetUserDetails(userListRepo)
*/

}