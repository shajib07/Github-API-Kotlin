package com.atahar.githubapi

import android.app.Application
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