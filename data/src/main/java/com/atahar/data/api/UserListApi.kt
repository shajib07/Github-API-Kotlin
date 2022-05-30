package com.atahar.data.api

import com.atahar.entities.api.GithubUserJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserListApi {

    @GET("/users")
    suspend fun getUserList(
        @Query("since") since: Int
    ): Response<List<GithubUserJson>>
}