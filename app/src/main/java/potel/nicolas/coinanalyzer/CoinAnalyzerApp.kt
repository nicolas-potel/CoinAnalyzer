package potel.nicolas.coinanalyzer

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import potel.nicolas.coinanalyzer.components.navigation.AppNavigation
import potel.nicolas.coinanalyzer.components.navigation.NavigationMenu
import potel.nicolas.coinanalyzer.components.TopNavbar
import potel.nicolas.coinanalyzer.config.PagesWithoutTopBar
import potel.nicolas.coinanalyzer.util.ViewModels

/**
 * Main component for Coin Analyzer application.
 */
@Composable
fun CoinAnalyzerApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val configuration = LocalConfiguration.current
    LaunchedEffect(configuration.orientation) {
        ViewModels.userPreferencesViewModel.updateOrientation(configuration.orientation)
    }

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
            )
        }
    }
}