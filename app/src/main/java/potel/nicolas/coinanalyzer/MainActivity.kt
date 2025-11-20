package potel.nicolas.coinanalyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import potel.nicolas.coinanalyzer.api.CryptoViewModel
import potel.nicolas.coinanalyzer.config.AppDatabase
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoRepository
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoViewModel
import potel.nicolas.coinanalyzer.preferences.UserPreferencesRepository
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel
import potel.nicolas.coinanalyzer.recentsearch.RecentSearchRepository
import potel.nicolas.coinanalyzer.recentsearch.RecentSearchViewModel
import potel.nicolas.coinanalyzer.ui.theme.ApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Room database
        val appDatabase = AppDatabase.getDatabase(this)

        // Repositories
        val userPreferenciesRepository = UserPreferencesRepository(this)
        val favoriteCryptoRepository = FavoriteCryptoRepository(appDatabase.favoriteDao())
        val recentSearchRepository = RecentSearchRepository(appDatabase.recentSearchDao())

        // ViewModels
        val userPreferencesViewModel = UserPreferencesViewModel(userPreferenciesRepository)
        val cryptoViewModel = CryptoViewModel(userPreferencesViewModel)
        val favoriteCryptoViewModel = FavoriteCryptoViewModel(favoriteCryptoRepository)
        val recentSearchViewModel = RecentSearchViewModel(recentSearchRepository)

        setContent {
            ApplicationTheme {
                CoinAnalyzerApp(
                    userPreferencesViewModel,
                    cryptoViewModel,
                    favoriteCryptoViewModel,
                    recentSearchViewModel
                )
            }
        }
    }

}