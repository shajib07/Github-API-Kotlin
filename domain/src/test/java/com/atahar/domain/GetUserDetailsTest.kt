package com.atahar.domain

import com.atahar.domain.common.Result
import com.atahar.domain.usecases.GetUserDetails
import com.atahar.domain.usecases.GetUserList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetUserDetailsTest {

    private lateinit var getUserDetails: GetUserDetails

    @Before
    fun setUp() {
        getUserDetails = GetUserDetails(FakeUserList())
    }

    @Test
    fun `get user details from remote with success`() {
        runBlocking {
            val result = getUserDetails.invoke("token", "login")
            val isSuccess = result is Result.Success
            Assert.assertTrue(isSuccess)
        }
    }

    @Test
    fun `get user details from remote`() {
        runBlocking {
            when (val result = getUserDetails.invoke("token", "login")) {
                is Result.Success -> {
                    Assert.assertNotEquals(result.data.name, "")
                }
                is Result.Error -> {
                    Assert.assertNotNull(result.exception.message)
                }
            }
        }
    }

}