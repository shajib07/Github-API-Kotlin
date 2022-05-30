package com.atahar.githubapi.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.atahar.githubapi.R
import com.atahar.githubapi.databinding.FragmentUserDetailsBinding
import com.atahar.githubapi.utils.AppConfig
import com.atahar.githubapi.utils.HelperFunctions.showErrorMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private val userDetailsViewModel: UserDetailsViewModel by viewModels()

    private lateinit var sharedPref: SharedPreferences
    private lateinit var token: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        sharedPref =
            requireActivity().getSharedPreferences(AppConfig.SHARED_PREF, Context.MODE_PRIVATE)
        token = "token ${sharedPref.getString(AppConfig.ACCESS_TOKEN, "")}"

        val login: String = arguments?.getString("userId") ?: ""

        val binding = FragmentUserDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = userDetailsViewModel

        userDetailsViewModel.status.observe(viewLifecycleOwner) { loadingStatus ->
            if (loadingStatus == LoadingStatus.LOADING) {
                binding.loadMoreProgress.visibility = View.VISIBLE
            } else if (loadingStatus == LoadingStatus.DONE || loadingStatus == LoadingStatus.ERROR) {
                binding.loadMoreProgress.visibility = View.GONE
            } else {
                binding.loadMoreProgress.visibility = View.GONE
                showErrorMessage(binding.root, getString(R.string.error_no_data))
            }

        }

        userDetailsViewModel.getUserDetails(token, login)

        return binding.root
    }
}