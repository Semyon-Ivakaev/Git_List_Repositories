package com.vertigo.gitlistrepositories.data.api

import com.vertigo.gitlistrepositories.data.model.CommitResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface CommitApiService {
    @GET
    fun getCommit(
        @Url url: String
    ): Call<List<CommitResponse>>
}