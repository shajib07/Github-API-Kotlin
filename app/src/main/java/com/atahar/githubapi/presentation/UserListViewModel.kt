package com.atahar.githubapi.presentation

import androidx.lifecycle.*
import com.atahar.domain.common.Result
import com.atahar.domain.usecases.GetUserList
import com.atahar.entities.GithubUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUserList: GetUserList
) : ViewModel() {

    val _status = MutableLiveData<LoadingStatus>(LoadingStatus.LOADING)
    val status: LiveData<LoadingStatus>
        get() = _status

    private var _userSince = 0
    val userSince: Int
        get() = _userSince

    private val _githubUsers = MutableLiveData<ArrayList<GithubUser>>()
    val githubUsers: LiveData<ArrayList<GithubUser>>
        get() = _githubUsers

    init {
        _githubUsers.value = arrayListOf()
    }

    fun getUserList() {
        viewModelScope.launch {
            if (githubUsers.value?.isEmpty() == true)
                _status.value = LoadingStatus.LOADING

            when (val result = getUserList.invoke(since = userSince)) {
                is Result.Success -> {
                    _status.value = LoadingStatus.DONE

                    val data = result.data
                    _githubUsers.value!!.addAll(data)
                    _githubUsers.value = _githubUsers.value
                    _userSince = data.last().id
                }
                is Result.Error -> {
                    _status.value = LoadingStatus.ERROR
                }
            }
        }
    }

}