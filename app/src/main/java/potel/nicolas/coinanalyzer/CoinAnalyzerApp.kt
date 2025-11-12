package potel.nicolas.coinanalyzer

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import potel.nicolas.coinanalyzer.api.CryptoViewModel
import potel.nicolas.coinanalyzer.components.navigation.AppNavigation
import potel.nicolas.coinanalyzer.components.navigation.NavigationMenu
import potel.nicolas.coinanalyzer.components.TopNavbar
import potel.nicolas.coinanalyzer.config.PagesWithoutTopBar
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoViewModel
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel

/**
 * Main component for Coin Analyzer application.
 */
@Composable
fun CoinAnalyzerApp(
    userPreferencesViewModel: UserPreferencesViewModel,
    cryptoViewModel: CryptoViewModel,
    favoriteCryptoViewModel: FavoriteCryptoViewModel
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMenu(navController, drawerState)
        },
    ) {
        Scaffold(
            topBar = {
                if (currentRoute !in PagesWithoutTopBar) {
                    TopNavbar(navController, drawerState)
                }
            },
        ) { innerPadding ->
            AppNavigation(
                modifier = Modifier
                    .padding(innerPadding),
                navController,
                userPreferencesViewModel,
                cryptoViewModel,
                favoriteCryptoViewModel
            )
        }
    }
}