package com.vertigo.gitlistrepositories.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vertigo.gitlistrepositories.data.model.Repository
import com.vertigo.gitlistrepositories.databinding.ItemListRepositoriesBinding
import com.vertigo.gitlistrepositories.ui.fragments.FragmentListRepositoryClickListener

class PaginationAdapter(private val listItemClickListener: FragmentListRepositoryClickListener?)
    : ListAdapter<Repository, RecyclerView.ViewHolder>(ListItemCallback()) {

    class ListItemCallback : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.avatarUrl == newItem.avatarUrl && oldItem.commitUrl == newItem.commitUrl &&
                    oldItem.fullName == newItem.fullName && oldItem.login == newItem.login
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ItemListRepositoriesViewHolder).bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListRepositoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListRepositoriesBinding.inflate(inflater, parent, false)
        return ItemListRepositoriesViewHolder(binding, listItemClickListener)
    }
    }