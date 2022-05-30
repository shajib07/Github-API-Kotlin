package com.atahar.data.api

import com.atahar.entities.api.GithubUserJson
import retrofit2.Response
import retrofit2.http.*

interface UserDetailsApi {

    @Headers("Content-Type: application/json")
    @GET("/users/{user}")
    suspend fun getUserDetails(
        @Header("Authorization") token: String,
        @Path("user") user: String
    ): Response<GithubUserJson>


}