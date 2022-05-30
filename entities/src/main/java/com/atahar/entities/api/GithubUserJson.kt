package com.atahar.entities.api

import com.squareup.moshi.Json

data class GithubUserJson(
    @Json(name = "name") val name: String?,
    @Json(name = "company") val company: String?,
    @Json(name = "location") val location: String?,
    @Json(name = "login") val login: String,
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "followers") val followers: Int?,
    @Json(name = "following") val following: Int?,
    @Json(name = "public_repos") val publicRepos: Int?,
    @Json(name = "id") val id: Int
)
