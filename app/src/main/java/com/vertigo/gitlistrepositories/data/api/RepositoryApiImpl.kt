package com.vertigo.gitlistrepositories.data.api

import android.util.Log
import com.vertigo.gitlistrepositories.data.model.Repository
import com.vertigo.gitlistrepositories.data.model.RepositoryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

private const val DEFAULT_SINCE = "0"

object RepositoryApiImpl {
    private var since: String? = null

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val repositoryApiService = retrofit.create(RepositoryApiService::class.java)

    suspend fun getRepositories(): MutableList<Repository> {
        val list = mutableListOf<Repository>()
        try {
            withContext(Dispatchers.Default) {
                val request = if (since == null) {
                    getRequest(DEFAULT_SINCE)
                } else {
                    getRequest(since!!)
                }
                if (request.isSuccessful) {
                    since = parcelableSince(request.headers().get("link").toString())

                    request.body()?.map { result ->
                        list.add(
                            Repository(
                                result.owner.avatar_url,
                                result.full_name,
                                result.owner.login,
                                result.commits_url.replace("{/sha}", "")
                            )
                        )
                    }
                }
            }
        }catch (e: Exception) {
            Log.e("ERROR", e.message.toString())
        }
        return list
    }

    private fun getRequest(sin: String): Response<MutableList<RepositoryResponse>> {
        return repositoryApiService.getRepositories(sin).execute()
    }

    private fun parcelableSince(link: String): String {
        val pattern = "<([\\s\\S]+?)>".toRegex()
        val resultSince = pattern.find(link)

        return resultSince?.value?.substringAfter("since=")?.replace(">", "").toString()
    }
}