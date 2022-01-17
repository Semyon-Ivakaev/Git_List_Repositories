package com.vertigo.gitlistrepositories.ui.adapters

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vertigo.gitlistrepositories.R
import com.vertigo.gitlistrepositories.data.model.Repository
import com.vertigo.gitlistrepositories.databinding.ItemListRepositoriesBinding
import com.vertigo.gitlistrepositories.ui.fragments.FragmentListRepositoryClickListener

class ItemListRepositoriesViewHolder(private val binding: ItemListRepositoriesBinding, private val listItemClickListener: FragmentListRepositoryClickListener?) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Repository) {
        with(binding) {
            loadAvatar(item.avatarUrl, listAvatarImageview)
            listLoginTextview.text = item.login
            listFullName.text = item.fullName
        }

        itemView.setOnClickListener {
            listItemClickListener?.onItemClicked(item)
        }
    }

    private fun loadAvatar(url: String, avatar: ImageView) {
        Glide.with(binding.root)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .error(R.drawable.ic_user_photo_error)
            .placeholder(R.drawable.ic_user_photo_error)
            .into(avatar)
    }
}