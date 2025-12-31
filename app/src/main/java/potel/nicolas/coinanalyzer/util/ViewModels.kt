package potel.nicolas.coinanalyzer.util

import potel.nicolas.coinanalyzer.MainApplication
import potel.nicolas.coinanalyzer.api.CryptoViewModel
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoViewModel
import potel.nicolas.coinanalyzer.preferences.LanguageViewModel
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel
import potel.nicolas.coinanalyzer.recentsearch.RecentSearchViewModel

object ViewModels {

    private lateinit var app: MainApplication

    fun init(application: MainApplication) {
        app = application

        userPreferencesViewModel = UserPreferencesViewModel(app.userPreferencesRepository)
        cryptoViewModel = CryptoViewModel(app.cryptoEntityRepository, userPreferencesViewModel)
        favoriteCryptoViewModel = FavoriteCryptoViewModel(app.favoriteCryptoRepository)
        recentSearchViewModel = RecentSearchViewModel(app.recentSearchRepository)
        languageViewModel = LanguageViewModel()
    }

    lateinit var userPreferencesViewModel: UserPreferencesViewModel private set
    lateinit var cryptoViewModel: CryptoViewModel private set
    lateinit var favoriteCryptoViewModel: FavoriteCryptoViewModel private set
    lateinit var recentSearchViewModel: RecentSearchViewModel private set
    lateinit var languageViewModel: LanguageViewModel private set
}

