package com.vertigo.gitlistrepositories.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.vertigo.gitlistrepositories.databinding.ItemShaBinding

class ShaViewHolder(private val binding: ItemShaBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        with(binding) {
            shaRecyclerItem.text = item
        }
    }
}