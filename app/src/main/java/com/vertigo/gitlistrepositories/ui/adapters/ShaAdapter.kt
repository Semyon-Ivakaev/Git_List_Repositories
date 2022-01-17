package com.vertigo.gitlistrepositories.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vertigo.gitlistrepositories.databinding.ItemShaBinding

class ShaAdapter: RecyclerView.Adapter<ShaViewHolder>() {

    var shas: List<String> = emptyList()
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShaViewHolder {
        Log.v("App", "onCreateViewHolder")
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemShaBinding.inflate(inflater, parent, false)
        return ShaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShaViewHolder, position: Int) {
        Log.v("App", "onBindViewHolder")
        holder.bind(shas.get(position))
    }

    override fun getItemCount(): Int = shas.size
}