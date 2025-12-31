package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.api.CryptoViewModel
import potel.nicolas.coinanalyzer.components.SearchBar
import potel.nicolas.coinanalyzer.components.SearchedCrypto
import potel.nicolas.coinanalyzer.components.SectionTitle
import potel.nicolas.coinanalyzer.config.Routes
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel
import potel.nicolas.coinanalyzer.recentsearch.RecentSearchViewModel
import potel.nicolas.coinanalyzer.ui.theme.applicationTheme
import potel.nicolas.coinanalyzer.util.ViewModels

@Composable
fun SearchPage(
    navController: NavHostController,
    cryptoViewModel: CryptoViewModel = ViewModels.cryptoViewModel,
    recentSearchViewModel: RecentSearchViewModel = ViewModels.recentSearchViewModel,
    userPreferencesViewModel: UserPreferencesViewModel = ViewModels.userPreferencesViewModel
) {
    var searchText by remember { mutableStateOf("") }

    val cryptos by cryptoViewModel.cryptos.collectAsState()
    val recentSearches by recentSearchViewModel.recentSearches.collectAsState()
    val selectedFilter by userPreferencesViewModel.filter.collectAsState()
    val selectedCurrency by userPreferencesViewModel.currency.collectAsState()

    val nbOfDisplayedCryptos = 10

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier,
        ) {
            SearchBar(
                value = searchText,
                onChange = { searchText = it },
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = { navController.navigate(Routes.FILTERS) },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.tune),
                    contentDescription = "Filtering icon",
                    tint = applicationTheme.font,
                    modifier = Modifier
                        .size(26.dp)
                )
            }
        }
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (!searchText.isEmpty()) {
                selectedFilter.sort(cryptos, selectedCurrency)
                    .filter { c ->
                        c.name.contains(searchText, ignoreCase = true)
                                || c.symbol.contains(searchText, ignoreCase = true)
                    }
                    .take(nbOfDisplayedCryptos)
                    .forEach { c ->
                        SearchedCrypto(c, navController)
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
                .forEach { SearchedCrypto(
                            cryptoViewModel.getDataFromCryptoItem(it),
                            navController)
                }
        }
    }
}