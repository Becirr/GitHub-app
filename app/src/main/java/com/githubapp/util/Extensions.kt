package com.githubapp.util

import android.content.Context
import android.text.format.DateUtils
import androidx.annotation.StringRes
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String?.getFormattedDate(context: Context, @StringRes textId: Int) : String {
    if (this == null) return ""
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("GMT")
    try {
        val time: Long? = sdf.parse(this)?.time
        val now = System.currentTimeMillis()
        if (time != null) {
            val ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
            return context.getString(textId, ago)
        }
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return ""
}