package com.sample.jet2ttinterview.utility

import android.content.Context
import com.sample.jet2ttinterview.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class ArticleBlogUtility {
    companion object {
        fun getTimeDifference(createdAtTime: String, context: Context): String{
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            try {
                val time: Long? = sdf.parse(createdAtTime)?.time
                val now = System.currentTimeMillis()
                time?.let {
//                        val timeAgo = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
                    val seconds: Long = TimeUnit.MILLISECONDS.toSeconds(now - time)
                    val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(now - time)
                    val hours: Long = TimeUnit.MILLISECONDS.toHours(now - time)
                    val days: Long = TimeUnit.MILLISECONDS.toDays(now - time)
                    when {
                        seconds < 60 -> {
                            return String.format(context.getString(R.string.seconds_ago), seconds.toInt())
                        }
                        minutes < 60 -> {
                            return String.format(context.getString(R.string.min_ago), minutes.toInt())
                        }
                        hours < 24 -> {
                            return String.format(context.getString(R.string.hour_ago), hours.toInt())
                        }
                        days > 7 -> {
                            return when {
                                days > 360 -> {
                                    String.format(context.getString(R.string.year_ago), (days / 360).toString().toInt())
                                }
                                days > 30 -> {
                                    String.format(context.getString(R.string.month_ago), (days / 30).toString().toInt())
                                }
                                else -> {
                                    String.format(context.getString(R.string.week_ago), (days / 7).toString().toInt())
                                }
                            }
                        }
                        else -> {
                            return String.format(context.getString(R.string.days_ago), days.toString().toInt())
                        }
                    }
                }
                return ""

            } catch (e: ParseException) {
                e.printStackTrace()
                return ""
            }
        }

        fun getFormattedLikes(count: String, context: Context): String {
            val countLike = count.toFloat()
            var totalCount = String.format(context.getString(R.string.likes), 0.0f)
            if(countLike > 1000F) {
                totalCount = String.format(context.getString(R.string.likes), (countLike/1000F))
            }
            return totalCount
        }

        fun getFormattedComments(count: String, context: Context): String {
            val countComments = count.toInt()
            var totalCount = String.format(context.getString(R.string.comments), 0)
            if(countComments > 1000) {
                totalCount = String.format(context.getString(R.string.comments), (countComments/1000))
            }
            return totalCount
        }
    }
}