package potel.nicolas.coinanalyzer.util

import potel.nicolas.coinanalyzer.MainApplication
import potel.nicolas.coinanalyzer.api.CryptoViewModel
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoViewModel
import potel.nicolas.coinanalyzer.preferences.LanguageViewModel
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel
import potel.nicolas.coinanalyzer.recentsearch.RecentSearchViewModel

object ViewModels {

    private lateinit var app: MainApplication

    fun init(application: MainApplication) { app = application }

    val userPreferencesViewModel: UserPreferencesViewModel by lazy { UserPreferencesViewModel(app.userPreferenciesRepository) }
    val cryptoViewModel: CryptoViewModel by lazy { CryptoViewModel(userPreferencesViewModel) }
    val favoriteCryptoViewModel: FavoriteCryptoViewModel by lazy { FavoriteCryptoViewModel(app.favoriteCryptoRepository) }
    val recentSearchViewModel: RecentSearchViewModel by lazy { RecentSearchViewModel(app.recentSearchRepository) }
    val languageViewModel: LanguageViewModel by lazy { LanguageViewModel() }
}

