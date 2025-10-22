package potel.nicolas.coinanalyzer.util

/**
 * Takes a string as input and returns it with the first letter capitalized.
 * @param input a string to be modified and returned.
 */
fun capitalizeFirstLetter(input: String): String {
    if (input.isEmpty()) return input
    return input[0].uppercaseChar() + input.substring(1)
}
