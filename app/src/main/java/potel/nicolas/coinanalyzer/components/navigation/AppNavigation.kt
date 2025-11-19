package potel.nicolas.coinanalyzer.components.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import potel.nicolas.coinanalyzer.api.CryptoViewModel
import potel.nicolas.coinanalyzer.config.Routes
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoViewModel
import potel.nicolas.coinanalyzer.pages.CoinsPage
import potel.nicolas.coinanalyzer.pages.CurrenciesPage
import potel.nicolas.coinanalyzer.pages.FavoritesPage
import potel.nicolas.coinanalyzer.pages.HomePage
import potel.nicolas.coinanalyzer.pages.LanguagesPage
import potel.nicolas.coinanalyzer.pages.OverviewPage
import potel.nicolas.coinanalyzer.pages.SearchPage
import potel.nicolas.coinanalyzer.pages.SettingsPage
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController : NavHostController,
    userPreferencesViewModel: UserPreferencesViewModel,
    cryptoViewModel: CryptoViewModel,
    favoriteCryptoViewModel: FavoriteCryptoViewModel
) {

    Box(modifier = modifier
        .fillMaxSize()
        .padding(8.dp)
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.HOME
        ) {
            composable(Routes.HOME) { HomePage(userPreferencesViewModel, cryptoViewModel, favoriteCryptoViewModel) }
            composable(Routes.COINS) { CoinsPage(userPreferencesViewModel, cryptoViewModel, favoriteCryptoViewModel) }
            composable(Routes.FAVORITES) { FavoritesPage(favoriteCryptoViewModel, userPreferencesViewModel, cryptoViewModel) }
            composable(Routes.OVERVIEW) { OverviewPage() }
            composable(Routes.SEARCH) { SearchPage(cryptoViewModel) }
            composable(Routes.SETTINGS) { SettingsPage(navController, userPreferencesViewModel) }
            composable(Routes.CURRENCIES) { CurrenciesPage(navController, userPreferencesViewModel) }
            composable(Routes.LANGUAGES) { LanguagesPage(navController) }
        }
    }
}