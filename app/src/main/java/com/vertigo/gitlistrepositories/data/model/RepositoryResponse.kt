package com.vertigo.gitlistrepositories.data.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("full_name")
    val full_name: String,
    @SerializedName("commits_url")
    val commits_url: String
)

data class Owner(
    @SerializedName("avatar_url")
    val avatar_url: String,
    @SerializedName("login")
    val login: String,
)