package potel.nicolas.coinanalyzer.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import potel.nicolas.coinanalyzer.ui.theme.applicationTheme

// Because we don't mind using experimental API for this project.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavbar(drawerState: DrawerState) {

    val scope = rememberCoroutineScope()
    val iconSize = 28
    val iconColor = applicationTheme.font

    CenterAlignedTopAppBar(
        title = { },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (drawerState.isClosed)
                                drawerState.open()
                            else drawerState.close()
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu navbar icon",
                    modifier = Modifier.size(iconSize.dp),
                    tint = iconColor,
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search navbar Icon",
                    modifier = Modifier.size(iconSize.dp),
                    tint = iconColor,
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings navbar Icon",
                    modifier = Modifier.size(iconSize.dp),
                    tint = iconColor,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = applicationTheme.background
        )
    )
}
