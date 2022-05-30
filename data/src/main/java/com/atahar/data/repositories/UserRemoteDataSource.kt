package com.atahar.data.repositories

import com.atahar.domain.common.Result
import com.atahar.entities.GithubUser

interface UserRemoteDataSource {
    suspend fun getRemoteUserList(since: Int): Result<List<GithubUser>>
    suspend fun getRemoteUserDetails(token: String, user: String): Result<GithubUser>
}