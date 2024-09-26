package br.com.finius.domain.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime

class Date(val millis: Long) {

    @OptIn(FormatStringsInDatetimeFormats::class)
    fun format(
        timeZone: TimeZone = TimeZone.UTC,
        pattern: DatePattern = DatePattern.DAY_MONTH_YEAR
    ): String {
        val localDateTime = Instant.fromEpochMilliseconds(millis)
            .toLocalDateTime(timeZone)

        return localDateTime.format(
            LocalDateTime.Format { byUnicodePattern(pattern.pattern) }
        )
    }
}

enum class DatePattern(val pattern: String) {
    DAY_MONTH_YEAR(pattern = "dd/MM/yyyy")
}