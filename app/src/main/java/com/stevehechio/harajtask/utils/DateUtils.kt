package com.stevehechio.harajtask.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ceil

/**
 * Created by stevehechio on 7/20/21
 */
object DateUtils {
    private const val dateFormat = "yyyy/MM/dd hh:mm aa"
    fun getDate(milliSeconds: Long): String? {
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }

    fun timeSpent(timestamp: Long): String? {
        var timestampDifference = timestamp
        timestampDifference /= 1000
        var timeSpent: String? = ""
        var magnitude = 0

        when {
            timestampDifference < 3600 -> {
                magnitude = ceil((timestampDifference / 60).toDouble()).toInt()
                timeSpent = java.lang.String.format(
                    Locale.ENGLISH,
                    "%d min%s",
                    magnitude,
                    if (magnitude > 1) "s" else ""
                )
            }
            timestampDifference < 86400 -> {
                magnitude = ceil((timestampDifference / 3600).toDouble()).toInt()
                timeSpent = java.lang.String.format(
                    Locale.ENGLISH,
                    "%d hr%s",
                    magnitude,
                    if (magnitude > 1) "s" else ""
                )
            }
            timestampDifference < 1209600 -> {
                magnitude = ceil((timestampDifference / 86400).toDouble()).toInt()
                timeSpent = java.lang.String.format(
                    Locale.ENGLISH,
                    "%d day%s",
                    magnitude,
                    if (magnitude > 1) "s" else ""
                )
            }
            timestampDifference < 7776000 -> {
                magnitude = ceil((timestampDifference / 604800).toDouble()).toInt()
                timeSpent = java.lang.String.format(
                    Locale.ENGLISH,
                    "%d week%s",
                    magnitude,
                    if (magnitude > 1) "s" else ""
                )
            }
            timestampDifference < 63072000 -> {
                magnitude = ceil((timestampDifference / 2592000).toDouble()).toInt()
                timeSpent = java.lang.String.format(
                    Locale.ENGLISH,
                    "%d month%s",
                    magnitude,
                    if (magnitude > 1) "s" else ""
                )
            }
            else -> {
                magnitude = ceil((timestampDifference / 31536000).toDouble()).toInt()
                timeSpent = java.lang.String.format(
                    Locale.ENGLISH,
                    "%d year%s",
                    magnitude,
                    if (magnitude > 1) "s" else ""
                )
            }
        }
        return timeSpent
    }

}