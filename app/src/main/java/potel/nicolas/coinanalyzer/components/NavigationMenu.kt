package potel.nicolas.coinanalyzer.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.config.Routes
import potel.nicolas.coinanalyzer.ui.theme.applicationTheme

@Composable
fun NavigationMenu(
    navController : NavHostController,
) {

    val iconSize = 20
    val drawerItemsColors = NavigationDrawerItemDefaults.colors(
        unselectedContainerColor = Color.Transparent,
        unselectedIconColor = applicationTheme.font,
        selectedIconColor = applicationTheme.font,
        unselectedTextColor = applicationTheme.font,
    )

    ModalDrawerSheet (
        modifier = Modifier
            .width(250.dp)
            .fillMaxHeight(),
        drawerContainerColor = applicationTheme.background
    ) {
        NavigationDrawerItem(
            label = { Text(stringResource(R.string.page_home)) },
            selected = false,
            onClick = { navController.navigate(Routes.HOME) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Navigation drawer : home",
                    modifier = Modifier.size(iconSize.dp),
                )
            },
            colors = drawerItemsColors,
        )
        HorizontalGradientDivider()
    }
}
