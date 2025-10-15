package potel.nicolas.coinanalyzer.config

object Routes {
    const val HOME = "home"
    const val COINS = "coins"
    const val FAVORITES = "favorites"
    const val OVERVIEW = "overview"
    const val SEARCH = "search"
    const val SETTINGS = "settings"
    const val LANGUAGES = "languages"
    const val CURRENCIES = "currencies"
}

val PagesWithoutTopBar = listOf(
    Routes.CURRENCIES,
    Routes.LANGUAGES
)
