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
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.api.CryptoViewModel
import potel.nicolas.coinanalyzer.components.CryptoGridView
import potel.nicolas.coinanalyzer.components.CryptoListView
import potel.nicolas.coinanalyzer.components.ErrorMessage
import potel.nicolas.coinanalyzer.components.SectionTitle
import potel.nicolas.coinanalyzer.components.TimeIntervalSwitcher
import potel.nicolas.coinanalyzer.components.WaitingIndicator
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoViewModel
import potel.nicolas.coinanalyzer.model.TimeInterval
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel
import potel.nicolas.coinanalyzer.util.ViewModels

@Composable
fun FavoritesPage(
    navHostController: NavHostController,
    favoriteCryptoViewModel: FavoriteCryptoViewModel = ViewModels.favoriteCryptoViewModel,
    userPreferencesViewModel: UserPreferencesViewModel = ViewModels.userPreferencesViewModel,
    cryptoViewModel: CryptoViewModel = ViewModels.cryptoViewModel
) {
    val favoriteCryptos by favoriteCryptoViewModel.favorites.collectAsState()
    val isListView by userPreferencesViewModel.isListViewEnabled.collectAsState()
    val cryptos by cryptoViewModel.cryptos.collectAsState()

    val selectedCurrency by userPreferencesViewModel.currency.collectAsState()
    val selectedTimeInterval by userPreferencesViewModel.timeInterval.collectAsState()
    val selectedFilter by userPreferencesViewModel.filter.collectAsState()

    val favoriteIds = favoriteCryptos.map { it.id }.toSet()

    val favoriteCryptosAsCryptos = selectedFilter.sort(cryptos, selectedCurrency).filter { crypto ->
        favoriteIds.contains(crypto.id)
    }

    val isLoading by cryptoViewModel.isLoading.collectAsState()

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
            SectionTitle(stringResource(id = R.string.page_favorites))

            if (favoriteCryptos.isEmpty()) {
                ErrorMessage(stringResource(R.string.favorites_no_data))
            } else if (isListView) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(favoriteCryptosAsCryptos) { crypto ->
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
                    items(favoriteCryptosAsCryptos) { crypto ->
                        CryptoGridView(crypto, selectedCurrency, selectedTimeInterval, favoriteCryptoViewModel, cryptoViewModel, navHostController)
                    }
                }
            }
        }
    }
}