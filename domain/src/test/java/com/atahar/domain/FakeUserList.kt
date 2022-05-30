package com.atahar.domain

import com.atahar.domain.common.Result
import com.atahar.domain.repositories.UserRepo
import com.atahar.entities.GithubUser

class FakeUserList : UserRepo {

    override suspend fun getUserList(since: Int): Result<List<GithubUser>> {
        return getFakeUserList()
    }

    override suspend fun getUserDetails(token: String, user: String): Result<GithubUser> {
        return Result.Success(getFakeUser())
    }

    private fun getFakeUser() =
        GithubUser(
            name = "Hoffmann",
            company = "Google",
            location = "Munich",
            login = "hhmann002",
            avatarUrl = "hoffmanUrl",
            followers = 22,
            following = 45,
            publicRepos = 122,
            id = 344
        )

    private fun getFakeUserList(): Result<List<GithubUser>> {
        val users = arrayListOf(getFakeUser())
        return Result.Success(users)
    }

}