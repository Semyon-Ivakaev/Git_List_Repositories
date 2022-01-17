package com.vertigo.gitlistrepositories.data.api

import com.vertigo.gitlistrepositories.data.model.RepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryApiService {
    @GET("repositories")
    fun getRepositories(
        @Query("since") since: String
    ): Call<MutableList<RepositoryResponse>>
}