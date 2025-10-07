package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.components.SectionTitle

@Composable
fun OverviewPage() {
    Column {
        SectionTitle(stringResource(id = R.string.overview_title))
    }
}