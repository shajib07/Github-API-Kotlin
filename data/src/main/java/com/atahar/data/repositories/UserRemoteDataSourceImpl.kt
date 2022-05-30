package com.atahar.data.repositories

import com.atahar.data.api.UserDetailsApi
import com.atahar.data.api.UserListApi
import com.atahar.domain.common.Result
import com.atahar.entities.GithubUser
import com.atahar.entities.mapper.toData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserRemoteDataSourceImpl(
    private val userListApi: UserListApi,
    private val userDetailsApi: UserDetailsApi
) : UserRemoteDataSource {

    override suspend fun getRemoteUserList(since: Int): Result<List<GithubUser>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = userListApi.getUserList(since)
                if (response.isSuccessful) {
                    return@withContext Result.Success(
                        response.body()!!.map { it.toData() }
                    )
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (ex: Exception) {
                return@withContext Result.Error(ex)
            }
        }
    }

    override suspend fun getRemoteUserDetails(token: String, user: String): Result<GithubUser> {
        return withContext(Dispatchers.IO) {
            try {

                val response = userDetailsApi.getUserDetails(token, user)
                if (response.isSuccessful) {
                    return@withContext Result.Success(
                        response.body()!!.toData()
                    )
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (ex: Exception) {
                return@withContext Result.Error(ex)
            }
        }
    }
}