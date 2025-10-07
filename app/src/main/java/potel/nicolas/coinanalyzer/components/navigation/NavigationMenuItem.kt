package potel.nicolas.coinanalyzer.components.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import potel.nicolas.coinanalyzer.ui.theme.applicationTheme

/**
 * This component is used in the navigation menu and represents each item.
 */
@Composable
fun NavigationMenuItem(
    navController : NavHostController,
    drawerState: DrawerState,
    label : String,
    route: String,
    icon : ImageVector
) {
    val iconSize = 20
    val verticalPadding = 0;

    val drawerItemsColors = NavigationDrawerItemDefaults.colors(
        unselectedContainerColor = Color.Transparent,
        unselectedIconColor = applicationTheme.font,
        selectedIconColor = applicationTheme.font,
        unselectedTextColor = applicationTheme.font,
    )

    val scope = rememberCoroutineScope()

    NavigationDrawerItem(
        label = { Text(label) },
        selected = false,
        onClick = {
            navController.navigate(route)
            scope.launch {
                drawerState.apply {
                    drawerState.close()
                }
            }
                  },
        modifier = Modifier.padding(vertical = verticalPadding.dp),
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = "Navigation menu item : $label",
                modifier = Modifier.size(iconSize.dp),
            )
        },
        colors = drawerItemsColors,
    )
}