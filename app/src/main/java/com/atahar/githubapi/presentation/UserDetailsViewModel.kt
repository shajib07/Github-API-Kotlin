package com.atahar.githubapi.presentation

import androidx.lifecycle.*
import com.atahar.domain.common.Result
import com.atahar.domain.usecases.GetUserDetails
import com.atahar.entities.GithubUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class LoadingStatus { LOADING, ERROR, DONE, NONE }

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getUserDetails: GetUserDetails
) : ViewModel() {

    val _status = MutableLiveData<LoadingStatus>(LoadingStatus.LOADING)
    val status: LiveData<LoadingStatus>
        get() = _status

    private val _githubUser = MutableLiveData<GithubUser>()
    val githubUser: LiveData<GithubUser>
        get() = _githubUser


    fun getUserDetails(token: String, login: String) {
        viewModelScope.launch {
            _status.value = LoadingStatus.LOADING

            when (val result = getUserDetails.invoke(token, login)) {
                is Result.Success -> {
                    _status.value = LoadingStatus.DONE
                    _githubUser.value = result.data!!
                }
                is Result.Error -> {
                    _status.value = LoadingStatus.ERROR
                }
            }
        }
    }

}