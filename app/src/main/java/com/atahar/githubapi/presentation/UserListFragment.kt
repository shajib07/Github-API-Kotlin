package com.atahar.githubapi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.atahar.githubapi.R
import com.atahar.githubapi.databinding.FragmentUserListBinding
import com.atahar.githubapi.utils.HelperFunctions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

     private val userListViewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserListBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = userListViewModel


        val adapter = UserListAdapter(object : UserListAdapter.UserListClickListener {
            override fun onItemClicked(login: String) {
                openUserDetailsFragment(login)
            }
        })


        userListViewModel.status.observe(viewLifecycleOwner) { loadingStatus->
            if (loadingStatus == LoadingStatus.LOADING) {
                binding.loadMoreProgress.visibility = View.VISIBLE
            } else if (loadingStatus == LoadingStatus.DONE || loadingStatus == LoadingStatus.ERROR) {
                binding.loadMoreProgress.visibility = View.GONE
            } else {
                binding.loadMoreProgress.visibility = View.GONE
                HelperFunctions.showErrorMessage(binding.root, getString(R.string.error_no_data))
            }
        }

        binding.userListRecyclerview.adapter = adapter

        userListViewModel.githubUsers.observe(viewLifecycleOwner) {
            adapter.notifyDataSetChanged()
        }

        binding.userListRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.userListRecyclerview.canScrollVertically(1)) {
                    userListViewModel.getUserList()
                }
            }
        })
        return binding.root
    }

    fun openUserDetailsFragment(login: String) {
        (activity as LoginActivity).openUserDetailsFragment(login)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userListViewModel.getUserList()

    }
}