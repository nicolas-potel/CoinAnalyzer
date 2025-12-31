package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.components.CryptoGridView
import potel.nicolas.coinanalyzer.components.CryptoListView
import potel.nicolas.coinanalyzer.components.ErrorMessage
import potel.nicolas.coinanalyzer.components.SectionTitle
import potel.nicolas.coinanalyzer.components.TimeIntervalSwitcher
import potel.nicolas.coinanalyzer.components.WaitingIndicator
import potel.nicolas.coinanalyzer.util.ViewModels

@Composable
fun CoinsPage(
    navHostController: NavHostController
) {
    val userPreferencesViewModel = ViewModels.userPreferencesViewModel
    val cryptoViewModel = ViewModels.cryptoViewModel
    val favoriteCryptoViewModel = ViewModels.favoriteCryptoViewModel

    val cryptos by cryptoViewModel.cryptos.collectAsState()
    val isListView by userPreferencesViewModel.isListViewEnabled.collectAsState()

    val selectedCurrency by userPreferencesViewModel.currency.collectAsState()
    val selectedTimeInterval by userPreferencesViewModel.timeInterval.collectAsState()
    val selectedFilter by userPreferencesViewModel.filter.collectAsState()

    val filteredCryptos = selectedFilter.sort(cryptos, selectedCurrency)

    val isLoading by cryptoViewModel.isLoading.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isLoading),
        onRefresh = { cryptoViewModel.loadCryptos(selectedCurrency.symbol) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TimeIntervalSwitcher(
                selectedInterval = selectedTimeInterval,
                onSelect = { userPreferencesViewModel.setTimeInterval(it) }
            )

            if (isLoading) {
                WaitingIndicator()
            } else {
                SectionTitle(stringResource(id = R.string.page_coins))

                if (filteredCryptos.isEmpty()) {
                    ErrorMessage(stringResource(R.string.coins_no_data))
                } else if (isListView) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(filteredCryptos) { crypto ->
                            CryptoListView(crypto, selectedCurrency, selectedTimeInterval, favoriteCryptoViewModel, cryptoViewModel, navHostController)
                        }
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(filteredCryptos) { crypto ->
                            CryptoGridView(crypto, selectedCurrency, selectedTimeInterval, favoriteCryptoViewModel, cryptoViewModel, navHostController)
                        }
                    }
                }
            }
        }
    }
}