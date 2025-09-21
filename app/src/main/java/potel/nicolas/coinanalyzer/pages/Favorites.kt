package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.components.SectionTitle
import potel.nicolas.coinanalyzer.config.Routes

@Composable
fun FavoritesPage(navController: NavHostController) {
    Column {
        SectionTitle(stringResource(id = R.string.page_favorites))
        Button(onClick = { navController.navigate(Routes.HOME) }) {
            Text("Go to home page")
        }
    }
}