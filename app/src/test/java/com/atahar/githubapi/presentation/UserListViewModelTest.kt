package com.atahar.githubapi.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.atahar.domain.usecases.GetUserList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class UserListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private val getUserList = mock<GetUserList>()
    private lateinit var userListViewModel: UserListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        userListViewModel = UserListViewModel(getUserList)
        userListViewModel._status.value = LoadingStatus.LOADING
    }

    @Test
    fun `test initial loading status to loading`() = runBlocking {
        userListViewModel.status
        Assert.assertEquals(userListViewModel.status.value, LoadingStatus.LOADING)
    }

    @Test
    fun `test get user list return zero`() = runBlocking {
        userListViewModel.getUserList()
        Assert.assertEquals(userListViewModel.githubUsers.value?.size, 0)
    }

}