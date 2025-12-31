package potel.nicolas.coinanalyzer.util

import android.annotation.SuppressLint

/**
 * Takes a string as input and returns it with the first letter capitalized.
 * @param input a string to be modified and returned.
 */
fun capitalizeFirstLetter(input: String): String {
    if (input.isEmpty()) return input
    return input[0].uppercaseChar() + input.substring(1)
}

/**
 * Formats the specified percent into a formatted string.
 * @param percent The value to be formatted.
 * Examples:
 * 3.34% will return "+3.34%" and -3.34% will return "-3.34%"
 */
@SuppressLint("DefaultLocale")
fun formatPercentChange(percent: Double): String {
    val res = if (percent >= 0) "+" else ""
    return res + String.format("%.3f", percent) + "%"
}