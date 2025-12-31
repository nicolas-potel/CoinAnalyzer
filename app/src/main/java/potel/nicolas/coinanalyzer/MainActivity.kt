package potel.nicolas.coinanalyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import potel.nicolas.coinanalyzer.api.CryptoViewModel
import potel.nicolas.coinanalyzer.config.AppDatabase
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoRepository
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoViewModel
import potel.nicolas.coinanalyzer.preferences.UserPreferencesRepository
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel
import potel.nicolas.coinanalyzer.recentsearch.RecentSearchRepository
import potel.nicolas.coinanalyzer.recentsearch.RecentSearchViewModel
import potel.nicolas.coinanalyzer.ui.theme.ApplicationTheme
import potel.nicolas.coinanalyzer.util.ViewModels

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ApplicationTheme {
                CoinAnalyzerApp()
            }
        }
    }

}