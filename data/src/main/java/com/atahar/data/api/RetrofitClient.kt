package com.atahar.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.github.com"

object RetrofitClient {

    val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
    }

    val userListApi: UserListApi by lazy {
        retrofitBuilder
            .build()
            .create(UserListApi::class.java)
    }

    val userDetailsApi: UserDetailsApi by lazy {
        retrofitBuilder
            .build()
            .create(UserDetailsApi::class.java)
    }
}