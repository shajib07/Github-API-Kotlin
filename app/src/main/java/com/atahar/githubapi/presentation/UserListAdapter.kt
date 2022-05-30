package com.atahar.githubapi.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.atahar.entities.GithubUser
import com.atahar.githubapi.databinding.ItemUserListBinding
import com.atahar.githubapi.presentation.UserListAdapter.UserViewHolder

class UserListAdapter(
    private val userListClickListener: UserListClickListener
) : ListAdapter<GithubUser, UserViewHolder>(DiffCallback) {

    interface UserListClickListener {
        fun onItemClicked(login: String)
    }

    class UserViewHolder(
        private var itemUserListBinding: ItemUserListBinding
    ) : RecyclerView.ViewHolder(itemUserListBinding.root) {

        fun bind(user: GithubUser) {
            itemUserListBinding.user = user
            itemUserListBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder = UserViewHolder(
        ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.itemView.setOnClickListener {
            userListClickListener.onItemClicked(user.login)
        }
        holder.bind(user)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<GithubUser>() {
        override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
            return oldItem.id == newItem.id
        }
    }


}