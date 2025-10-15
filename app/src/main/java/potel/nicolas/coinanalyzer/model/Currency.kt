package potel.nicolas.coinanalyzer.model

enum class Currency(val symbol: String, val displayName: String) {
    USD("$", "USD"),
    EUR("€", "EUR"),
}