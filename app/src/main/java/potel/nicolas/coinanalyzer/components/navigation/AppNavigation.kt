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
import potel.nicolas.coinanalyzer.pages.FiltersPage
import potel.nicolas.coinanalyzer.pages.HomePage
import potel.nicolas.coinanalyzer.pages.LanguagesPage
import potel.nicolas.coinanalyzer.pages.OverviewPage
import potel.nicolas.coinanalyzer.pages.SearchPage
import potel.nicolas.coinanalyzer.pages.SettingsPage

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController : NavHostController,
) {

    Box(modifier = modifier
        .fillMaxSize()
        .padding(8.dp)
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.HOME
        ) {
            composable(Routes.HOME) { HomePage(navController) }
            composable(Routes.COINS) { CoinsPage(navController) }
            composable(Routes.FAVORITES) { FavoritesPage(navController) }
            composable(Routes.OVERVIEW) { OverviewPage() }
            composable(Routes.SEARCH) { SearchPage(navController) }
            composable(Routes.SETTINGS) { SettingsPage(navController) }
            composable(Routes.CURRENCIES) { CurrenciesPage(navController) }
            composable(Routes.LANGUAGES) { LanguagesPage(navController) }
            composable(Routes.FILTERS) { FiltersPage(navController) }
        }
    }
}