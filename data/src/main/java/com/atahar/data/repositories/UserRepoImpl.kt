package com.atahar.data.repositories

import com.atahar.domain.common.Result
import com.atahar.domain.repositories.UserRepo
import com.atahar.entities.GithubUser

class UserRepoImpl(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepo {

    override suspend fun getUserList(since: Int): Result<List<GithubUser>> {
        return userRemoteDataSource.getRemoteUserList(since)
    }

    override suspend fun getUserDetails(token: String, user: String): Result<GithubUser> {
        return userRemoteDataSource.getRemoteUserDetails(token, user)
    }
}