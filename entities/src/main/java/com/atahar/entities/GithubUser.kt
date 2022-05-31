package com.atahar.entities

data class GithubUser(
    val name: String?,
    val company: String?,
    val location: String?,
    val login: String,
    val avatarUrl: String,
    val followers: Int?,
    val following: Int?,
    val publicRepos: Int?,
    val id: Int
)