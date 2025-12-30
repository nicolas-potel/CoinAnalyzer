package potel.nicolas.coinanalyzer.model

import potel.nicolas.coinanalyzer.R
import java.time.Instant

enum class TimeInterval(val id: String, val displayName: Int, val every: String, val timeIntervalAsSeconds: Long) {
    /**
     * The "every" property stands for every each time we take the prices.
     * It is calculated to be around 500/600 values for each possible interval.
     *
     * For example, for the HOUR time interval, we are getting
     * the price every 5 minutes from 1 hour to now.
     */
    HOUR("hour", R.string.time_interval_hour, "5m", 3600),
    DAY("day", R.string.time_interval_day, "45m", 86400),
    WEEK("week", R.string.time_interval_week, "6h", 604800),
    MONTH("month", R.string.time_interval_month, "1d", 2592000);

    companion object {
        fun from(id: String): TimeInterval {
            return TimeInterval.entries.find { it.id == id }
                ?: throw IllegalArgumentException("Unknown time interval: $id")
        }

        fun getTimeFromInterval(interval: TimeInterval): Instant {
            return Instant.now().minusSeconds(interval.timeIntervalAsSeconds)
        }
    }
}