package com.vertigo.gitlistrepositories.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Commit(
    val commitMessage: String,
    val commitAuthor: String,
    val commitDate: String,
    val commitSha: List<String>
): Parcelable