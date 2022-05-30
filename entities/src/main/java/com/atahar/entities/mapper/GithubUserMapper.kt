package com.atahar.entities.mapper

import com.atahar.entities.GithubUser
import com.atahar.entities.api.GithubUserJson
import com.squareup.moshi.Json

fun GithubUserJson.toData() =
    GithubUser(
        name = name,
        company = company,
        location = location,
        login = login,
        avatarUrl = avatarUrl,
        followers = followers,
        following = following,
        publicRepos = publicRepos,
        id = id
    )
