package com.vertigo.gitlistrepositories.ui.fragments

import com.vertigo.gitlistrepositories.data.model.Repository

interface FragmentListRepositoryClickListener {
    fun onItemClicked(item: Repository)
}