package potel.nicolas.coinanalyzer.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import potel.nicolas.coinanalyzer.config.Routes
import potel.nicolas.coinanalyzer.pages.FavoritesPage
import potel.nicolas.coinanalyzer.pages.HomePage
import potel.nicolas.coinanalyzer.pages.SearchPage

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SEARCH
    ) {
        composable(Routes.HOME) {
            HomePage(navController)
        }
        composable(Routes.FAVORITES) {
            FavoritesPage(navController)
        }
        composable(Routes.SEARCH) {
            SearchPage(navController)
        }
    }
}