package com.vertigo.gitlistrepositories.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_FORMAT_24 = "dd.MM.YYYY"
private const val DATE_FORMAT_23 = "dd.MM.yyyy"
private const val API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

class DateParser {
    private val outFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        SimpleDateFormat(DATE_FORMAT_24, Locale.ENGLISH)
    } else {
        SimpleDateFormat(DATE_FORMAT_23, Locale.ENGLISH)
    }

    private val inputFormat = SimpleDateFormat(API_DATE_FORMAT, Locale.ENGLISH)

    fun getTrueDateFormat(currentDate: String): String {
        val result = outFormat.format(inputFormat.parse(currentDate))

        return result.toString()
    }
}