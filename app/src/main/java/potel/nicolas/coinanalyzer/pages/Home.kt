package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.components.SectionTitle

@Composable
fun HomePage(navController: NavHostController) {
    Column {
        SectionTitle(stringResource(id = R.string.page_home))
    }
}