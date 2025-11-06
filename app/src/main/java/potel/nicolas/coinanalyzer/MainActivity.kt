package potel.nicolas.coinanalyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import potel.nicolas.coinanalyzer.api.CryptoViewModel
import potel.nicolas.coinanalyzer.preferences.UserPreferencesRepository
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel
import potel.nicolas.coinanalyzer.ui.theme.ApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Repositories
        val userPreferenciesRepository = UserPreferencesRepository(this)


        // ViewModels
        val userPreferencesViewModel = UserPreferencesViewModel(userPreferenciesRepository)
        val cryptoViewModel = CryptoViewModel(userPreferencesViewModel)

        setContent {
            ApplicationTheme {
                CoinAnalyzerApp(userPreferencesViewModel, cryptoViewModel)
            }
        }
    }

}