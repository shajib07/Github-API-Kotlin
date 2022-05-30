package com.atahar.domain

import com.atahar.domain.common.Result
import com.atahar.domain.usecases.GetUserList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetUserListTest {

    private lateinit var getUserList: GetUserList

    @Before
    fun setUp() {
        getUserList = GetUserList(FakeUserList())
    }

    @Test
    fun `get usr list from remote with success`() {
        runBlocking {
            val result = getUserList.invoke(22)
            val isSuccess = result is Result.Success
            Assert.assertTrue(isSuccess)
        }
    }

    @Test
    fun `get users from remote`() {
        runBlocking {
            when (val result = getUserList.invoke(12)) {
                is Result.Success -> {
                    Assert.assertNotEquals(result.data.size, 0)
                }
                is Result.Error -> {
                    Assert.assertNotNull(result.exception.message)
                }
            }
        }
    }

}