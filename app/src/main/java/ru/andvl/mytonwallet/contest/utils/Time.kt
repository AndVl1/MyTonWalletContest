package ru.andvl.mytonwallet.contest.utils

import android.content.Context
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import ru.andvl.mytonwallet.contest.R
import java.time.format.DateTimeFormatter

fun getCurrentDate(): LocalDate {
    return Clock.System.todayIn(TimeZone.currentSystemDefault())
}

fun LocalDate.isToday(): Boolean {
    return this == getCurrentDate()
}

fun LocalDate.isYesterday(): Boolean {
    return this == getCurrentDate().minus(DatePeriod(days = 1))
}

fun formatHumanDay(date: LocalDate, context: Context): String {
    return when {
        date.isToday() -> context.getString(R.string.today)
        date.isYesterday() -> context.getString(R.string.yesterday)
        else -> formatFullDay(date)
    }
}

fun formatTime(dateTime: LocalDateTime): String {
    return "%02d:%02d".format(dateTime.hour, dateTime.minute)
}

fun formatFullDay(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern(
        if (date.year == getCurrentDate().year) "MMMM d" else "MMMM d, yyyy",
        getDefaultLocale()
    )

    return formatter.format(date.toJavaLocalDate())
}

fun timestampToDateTime(timestamp: Long): LocalDateTime {
    val instant: Instant = Instant.fromEpochMilliseconds(timestamp)

    val localDateTime: LocalDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

    return localDateTime
}