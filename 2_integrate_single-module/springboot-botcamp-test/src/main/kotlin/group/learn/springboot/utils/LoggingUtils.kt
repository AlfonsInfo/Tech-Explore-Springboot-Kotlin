package group.learn.springboot.utils

import org.springframework.stereotype.Component
import java.util.*


@Component
class LoggingUtils {
    fun logTemplate(position: String, action: String): String {
        return AppDateTimeFormat.timestamp() + "[" + position + "]" + " - " + "[" + action + "]".uppercase(Locale.getDefault())
    }

    fun logFunction(className: String, serviceName: String, position: String): String {
        return AppDateTimeFormat.timestamp() + "[ CLASS : " + className + "]" + " - " + "[ SERVICE " + serviceName + "]" + " - " + "[ POSITION " + position + "]".uppercase(
            Locale.getDefault()
        )
    }

    companion object {
        fun logData(data: Any): String {
            return "Hasil Query : $data"
        }
    }
}