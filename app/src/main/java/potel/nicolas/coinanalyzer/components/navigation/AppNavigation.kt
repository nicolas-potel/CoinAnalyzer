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
import potel.nicolas.coinanalyzer.config.Routes
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
    userPreferencesViewModel: UserPreferencesViewModel
) {

    Box(modifier = modifier
        .fillMaxSize()
        .padding(8.dp)
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.HOME
        ) {
            composable(Routes.HOME) { HomePage() }
            composable(Routes.COINS) { CoinsPage() }
            composable(Routes.FAVORITES) { FavoritesPage() }
            composable(Routes.OVERVIEW) { OverviewPage() }
            composable(Routes.SEARCH) { SearchPage() }
            composable(Routes.SETTINGS) { SettingsPage(navController, userPreferencesViewModel) }
            composable(Routes.CURRENCIES) { CurrenciesPage(navController, userPreferencesViewModel) }
            composable(Routes.LANGUAGES) { LanguagesPage(navController) }
        }
    }
}