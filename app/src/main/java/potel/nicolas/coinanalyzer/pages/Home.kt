package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.components.CryptoGridView
import potel.nicolas.coinanalyzer.components.CryptoListView
import potel.nicolas.coinanalyzer.components.SectionTitle
import potel.nicolas.coinanalyzer.model.crypto1
import potel.nicolas.coinanalyzer.model.crypto2
import potel.nicolas.coinanalyzer.model.crypto3

@Composable
fun HomePage(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionTitle(stringResource(id = R.string.page_home))
        CryptoListView(crypto1)
        CryptoListView(crypto2)
        CryptoListView(crypto3)

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CryptoGridView(crypto1)
            CryptoGridView(crypto2)
            CryptoGridView(crypto3)
            CryptoGridView(crypto3)
        }
    }
}