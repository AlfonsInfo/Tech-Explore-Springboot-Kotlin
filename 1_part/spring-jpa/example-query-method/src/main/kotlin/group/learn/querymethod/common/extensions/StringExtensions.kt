package group.learn.querymethod.common.extensions

import java.text.SimpleDateFormat
import java.util.*

    fun String.toDateRange(): Pair<Date?, Date?> {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale("id", "ID"))
    val dates = this.split(",")
    val startDate = dates.getOrNull(0)?.let { dateFormat.parse(it) }
    val endDate = dates.getOrNull(1)?.let { dateFormat.parse(it) }
    return Pair(startDate, endDate)
}
