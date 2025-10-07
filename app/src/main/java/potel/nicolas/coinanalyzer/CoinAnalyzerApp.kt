package potel.nicolas.coinanalyzer

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import potel.nicolas.coinanalyzer.components.AppNavigation
import potel.nicolas.coinanalyzer.components.NavigationMenu
import potel.nicolas.coinanalyzer.components.TopNavbar

/**
 * Main component for Coin Analyzer application.
 */
@Composable
fun CoinAnalyzerApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMenu(navController)
        },
    ) {
        Scaffold(
            topBar = { TopNavbar(drawerState = drawerState) },
        ) { innerPadding ->
            AppNavigation(
                modifier = Modifier
                    .padding(innerPadding),
                navController
            )
        }
    }
}