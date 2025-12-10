package potel.nicolas.coinanalyzer

import android.app.Application
import potel.nicolas.coinanalyzer.api.CryptoViewModel
import potel.nicolas.coinanalyzer.config.AppDatabase
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoRepository
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoViewModel
import potel.nicolas.coinanalyzer.preferences.LanguageViewModel
import potel.nicolas.coinanalyzer.preferences.UserPreferencesRepository
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel
import potel.nicolas.coinanalyzer.recentsearch.RecentSearchRepository
import potel.nicolas.coinanalyzer.recentsearch.RecentSearchViewModel
import potel.nicolas.coinanalyzer.util.ViewModels

class MainApplication : Application() {

    // Room database
    val appDatabase : AppDatabase by lazy { AppDatabase.getDatabase(this) }

    // Repositories
    val userPreferenciesRepository by lazy { UserPreferencesRepository(this) }
    val favoriteCryptoRepository by lazy { FavoriteCryptoRepository(appDatabase.favoriteDao()) }
    val recentSearchRepository by lazy { RecentSearchRepository(appDatabase.recentSearchDao()) }

    // ViewModels
    val userPreferencesViewModel by lazy { UserPreferencesViewModel(userPreferenciesRepository) }
    val cryptoViewModel by lazy { CryptoViewModel(userPreferencesViewModel) }
    val favoriteCryptoViewModel by lazy { FavoriteCryptoViewModel(favoriteCryptoRepository) }
    val recentSearchViewModel by lazy { RecentSearchViewModel(recentSearchRepository) }
    val languageViewModel by lazy { LanguageViewModel() }

    override fun onCreate() {
        super.onCreate()
        ViewModels.init(this)
    }

}