package com.example.retrofitroompaging3sampleproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitroompaging3sampleproject.R
import com.example.retrofitroompaging3sampleproject.databinding.UserCardBinding
import com.example.retrofitroompaging3sampleproject.model.User
import javax.inject.Inject

class MainAdapter @Inject constructor() : PagingDataAdapter<User, MainViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return DataBindingUtil.inflate<UserCardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.user_card,
            parent,
            false
        ).let { MainViewHolder(it) }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class MainViewHolder(private val binding: UserCardBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.user = user
    }
}