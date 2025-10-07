package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.components.SearchedCrypto
import potel.nicolas.coinanalyzer.components.SectionTitle
import potel.nicolas.coinanalyzer.model.crypto1
import potel.nicolas.coinanalyzer.model.crypto2
import potel.nicolas.coinanalyzer.model.crypto3

@Composable
fun SearchPage() {
    Column {
        SectionTitle(stringResource(id = R.string.page_search))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SearchedCrypto(crypto1)
            SearchedCrypto(crypto2)
            SearchedCrypto(crypto1)
            SearchedCrypto(crypto2)
            SearchedCrypto(crypto3)
        }
    }
}