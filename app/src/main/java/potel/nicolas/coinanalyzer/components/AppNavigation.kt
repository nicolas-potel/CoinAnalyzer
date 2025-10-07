package potel.nicolas.coinanalyzer.components

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
import potel.nicolas.coinanalyzer.pages.FavoritesPage
import potel.nicolas.coinanalyzer.pages.HomePage
import potel.nicolas.coinanalyzer.pages.SearchPage

@Composable
fun AppNavigation(modifier: Modifier = Modifier, navController : NavHostController) {

    Box(modifier = modifier
        .fillMaxSize()
        .padding(8.dp)
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.HOME
        ) {
            composable(Routes.HOME) {
                HomePage()
            }
            composable(Routes.FAVORITES) {
                FavoritesPage()
            }
            composable(Routes.SEARCH) {
                SearchPage()
            }
        }
    }
}