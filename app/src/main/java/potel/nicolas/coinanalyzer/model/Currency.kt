package potel.nicolas.coinanalyzer.model

enum class Currency(val symbol: String, val displayName: String) {
    USD("USD", "$"),
    EUR("EUR", "€");

    companion object {
        fun from(symbol: String): Currency {
            return Currency.entries.find { it.symbol == symbol }
                ?: throw IllegalArgumentException("Unknown currency symbol: $symbol")
        }
    }
}