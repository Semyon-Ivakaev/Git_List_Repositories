package com.vertigo.gitlistrepositories.data.api

import android.util.Log
import com.vertigo.gitlistrepositories.data.model.Commit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

object CommitApiImpl {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val commitApiService = retrofit.create(CommitApiService::class.java)

    suspend fun getCommit(url: String): List<Commit> {
        val commits = mutableListOf<Commit>()
        try {
            withContext(Dispatchers.Default) {
                val request = commitApiService.getCommit(url).execute()
                if (request.isSuccessful) {
                    request.body()?.map { result ->
                        commits.add(
                            Commit(
                                result.commitData.message,
                                result.commitData.author.name,
                                result.commitData.author.date,
                                result.parents.map { parents -> parents.sha })
                        )
                    }
                }
            }
        }catch (e: Exception) {
            Log.e("Error", e.message.toString())
        }

        return commits
    }
}