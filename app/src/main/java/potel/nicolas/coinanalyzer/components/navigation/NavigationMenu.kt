package potel.nicolas.coinanalyzer.components.navigation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.components.HorizontalGradientDivider
import potel.nicolas.coinanalyzer.config.Routes
import potel.nicolas.coinanalyzer.ui.theme.applicationTheme

/**
 * This component represents the navigation menu which can be opened in the top navbar.
 */
@Composable
fun NavigationMenu(
    navController : NavHostController,
    drawerState : DrawerState
) {

    ModalDrawerSheet (
        modifier = Modifier
            .width(250.dp)
            .fillMaxHeight(),
        drawerContainerColor = applicationTheme.background
    ) {

        NavigationMenuItem(
            navController,
            drawerState,
            stringResource(R.string.page_home),
            Routes.HOME,
            Icons.Default.Home
        )

        HorizontalGradientDivider()

        NavigationMenuItem(
            navController,
            drawerState,
            stringResource(R.string.page_coins),
            Routes.COINS,
            ImageVector.vectorResource(R.drawable.coin)
        )

        NavigationMenuItem(
            navController,
            drawerState,
            stringResource(R.string.page_favorites),
            Routes.FAVORITES,
            ImageVector.vectorResource(R.drawable.bookmark)
        )

        NavigationMenuItem(
            navController,
            drawerState,
            stringResource(R.string.page_search),
            Routes.SEARCH,
            Icons.Default.Search
        )

        NavigationMenuItem(
            navController,
            drawerState,
            stringResource(R.string.page_settings),
            Routes.SETTINGS,
            Icons.Default.Settings
        )

    }
}
