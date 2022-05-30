package com.atahar.domain.repositories

import com.atahar.domain.common.Result
import com.atahar.entities.GithubUser

interface UserRepo {
    suspend fun getUserList(since: Int): Result<List<GithubUser>>
    suspend fun getUserDetails(token: String, user: String): Result<GithubUser>
}