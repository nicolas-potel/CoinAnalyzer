package potel.nicolas.coinanalyzer.model

import potel.nicolas.coinanalyzer.R

enum class TimeInterval(val displayName: Int) {
    HOUR(R.string.time_interval_hour),
    DAY(R.string.time_interval_day),
    WEEK(R.string.time_interval_week),
    MONTH(R.string.time_interval_month);

    companion object {
        fun from(displayName: Int): TimeInterval {
            return TimeInterval.entries.find { it.displayName == displayName }
                ?: throw IllegalArgumentException("Unknown time interval: $displayName")
        }
    }
}