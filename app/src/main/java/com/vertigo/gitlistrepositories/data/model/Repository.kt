package com.vertigo.gitlistrepositories.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository(
    val avatarUrl: String,
    val fullName: String,
    val login: String,
    val commitUrl: String
): Parcelable
