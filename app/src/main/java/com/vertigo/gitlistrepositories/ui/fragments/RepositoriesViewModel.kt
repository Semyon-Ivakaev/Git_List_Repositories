package com.vertigo.gitlistrepositories.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vertigo.gitlistrepositories.data.api.RepositoryApiImpl
import com.vertigo.gitlistrepositories.data.model.Repository
import kotlinx.coroutines.launch

class RepositoriesViewModel: ViewModel() {
    private val _repositoriesViewModel: MutableLiveData<MutableList<Repository>> = MutableLiveData()
    val repositoriesViewModel: LiveData<MutableList<Repository>> get() = _repositoriesViewModel

    fun loadContent() {
        if (repositoriesViewModel.value == null) {
            viewModelScope.launch {
                _repositoriesViewModel.postValue(RepositoryApiImpl.getRepositories())
            }
        }
    }

    fun loadNewRepositories() {
        val list:MutableList<Repository>? = _repositoriesViewModel.value
        viewModelScope.launch {
            list?.addAll(RepositoryApiImpl.getRepositories() as Collection<Repository>)
            _repositoriesViewModel.postValue(list)
        }
    }
}