package com.atahar.domain.usecases

import com.atahar.domain.repositories.UserRepo

class GetUserDetails (
    private val userRepo: UserRepo
) {
    suspend operator fun invoke(token: String, user: String) =
        userRepo.getUserDetails(token, user)
}