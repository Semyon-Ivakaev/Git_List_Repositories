package com.vertigo.gitlistrepositories.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vertigo.gitlistrepositories.data.api.CommitApiImpl
import com.vertigo.gitlistrepositories.data.model.Commit
import kotlinx.coroutines.launch

class CommitViewModel: ViewModel() {
    private val commitLiveData = MutableLiveData<Commit>()
    val commit: LiveData<Commit> get() = commitLiveData

    fun initCommit(url: String) {
        viewModelScope.launch {
            val value = CommitApiImpl.getCommit(url)
            if (value.isNotEmpty()) {
                commitLiveData.postValue(value[0])
            } else {
                commitLiveData.postValue(Commit("Sorry!", "We can`t load commit",
                    "1970-01-01T00:00:00Z", listOf("List sha is Empty")))
            }
        }
    }
}