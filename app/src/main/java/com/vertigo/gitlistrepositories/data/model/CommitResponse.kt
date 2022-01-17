package com.vertigo.gitlistrepositories.data.model

import com.google.gson.annotations.SerializedName

data class CommitResponse(
    @SerializedName("commit")
    val commitData: CommitData,
    @SerializedName("parents")
    val parents: List<Parents>
)

data class CommitData(
    @SerializedName("author")
    val author: CommitAuthor,
    @SerializedName("message")
    val message: String
)

data class CommitAuthor(
    @SerializedName("name")
    val name: String,
    @SerializedName("date")
    val date: String
)

data class Parents(
    @SerializedName("sha")
    val sha: String
)