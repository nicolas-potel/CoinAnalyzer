package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.api.CryptoViewModel
import potel.nicolas.coinanalyzer.components.SearchBar
import potel.nicolas.coinanalyzer.components.SearchedCrypto
import potel.nicolas.coinanalyzer.components.SectionTitle
import potel.nicolas.coinanalyzer.recentsearch.RecentSearchViewModel

@Composable
fun SearchPage(
    cryptoViewModel: CryptoViewModel,
    recentSearchViewModel: RecentSearchViewModel
) {
    var searchText by remember { mutableStateOf("") }

    val cryptos by cryptoViewModel.cryptos.collectAsState()
    val recentSearches by recentSearchViewModel.recentSearches.collectAsState()

    val nbOfDisplayedCryptos = 10

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SearchBar(
            value = searchText,
            onChange = { searchText = it }
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (!searchText.isEmpty()) {
                cryptos
                    .filter { c ->
                        c.name.contains(searchText, ignoreCase = true)
                                || c.symbol.contains(searchText, ignoreCase = true)
                    }
                    .take(nbOfDisplayedCryptos)
                    .forEach { c ->
                        SearchedCrypto(c, recentSearchViewModel)
                    }
            }
        }
        SectionTitle(stringResource(id = R.string.search_title))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            recentSearches
                .forEach { SearchedCrypto(it, recentSearchViewModel) }
        }
    }
}