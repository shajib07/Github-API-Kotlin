package com.atahar.domain.usecases

import com.atahar.domain.repositories.UserRepo

class GetUserList(
    private val userRepo: UserRepo
) {
    suspend operator fun invoke(since: Int) = userRepo.getUserList(since)
}