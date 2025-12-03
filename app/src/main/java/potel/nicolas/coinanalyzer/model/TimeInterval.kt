package potel.nicolas.coinanalyzer.model

import potel.nicolas.coinanalyzer.R
import java.time.Instant

enum class TimeInterval(val displayName: Int, val every: String, val timeIntervalAsSeconds: Long) {
    /**
     * The "every" property stands for every each time we take the prices.
     * It is calculated to be around 500/600 values for each possible interval.
     *
     * For example, for the HOUR time interval, we are getting
     * the price every 5 minutes from 1 hour to now.
     */
    HOUR(R.string.time_interval_hour, "5m", 3600),
    DAY(R.string.time_interval_day, "45m", 86400),
    WEEK(R.string.time_interval_week, "6h", 604800),
    MONTH(R.string.time_interval_month, "1d", 2592000);

    companion object {
        fun from(displayName: Int): TimeInterval {
            return TimeInterval.entries.find { it.displayName == displayName }
                ?: throw IllegalArgumentException("Unknown time interval: $displayName")
        }

        fun getTimeFromInterval(interval: TimeInterval): Instant {
            return Instant.now().minusSeconds(interval.timeIntervalAsSeconds)
        }
    }
}