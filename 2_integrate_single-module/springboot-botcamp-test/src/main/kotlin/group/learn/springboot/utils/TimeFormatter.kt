package group.learn.springboot.utils

import org.springframework.stereotype.Component
import java.time.LocalDateTime

import java.time.format.DateTimeFormatter


@Component
object AppDateTimeFormat {
    fun timestamp(): String {
        val localDateTime = LocalDateTime.now()
        val dateTimeFormatter = DateTimeFormatter.ofPattern("dd MM yyyy hh:mm:ss")
        return dateTimeFormatter.format(localDateTime)
    }

    fun stringMapToLocalDateTime(input: String?): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val result = LocalDateTime.parse(input, formatter)
        return result
    }
}