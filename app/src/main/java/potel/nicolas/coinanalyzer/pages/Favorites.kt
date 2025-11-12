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
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.api.CryptoViewModel
import potel.nicolas.coinanalyzer.components.CryptoGridView
import potel.nicolas.coinanalyzer.components.CryptoListView
import potel.nicolas.coinanalyzer.components.SectionTitle
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoViewModel
import potel.nicolas.coinanalyzer.model.Currency
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel

@Composable
fun FavoritesPage(
    favoriteCryptoViewModel: FavoriteCryptoViewModel,
    userPreferencesViewModel: UserPreferencesViewModel,
    cryptoViewModel: CryptoViewModel
) {
    val favoriteCryptos by favoriteCryptoViewModel.favorites.collectAsState()
    val selectedCurrency by userPreferencesViewModel.currency.collectAsState()
    val isListView by userPreferencesViewModel.isListViewEnabled.collectAsState()
    val cryptos by cryptoViewModel.cryptos.collectAsState()

    val currencyAsCurrency = Currency.from(selectedCurrency)

    val favoriteIds = favoriteCryptos.map { it.id }.toSet()

    val favoriteCryptosAsCryptos = cryptos.filter { crypto ->
        favoriteIds.contains(crypto.id)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SectionTitle(stringResource(id = R.string.page_favorites))

        if (isListView) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(favoriteCryptosAsCryptos) { crypto ->
                    CryptoListView(crypto, currencyAsCurrency, favoriteCryptoViewModel)
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
                    CryptoGridView(crypto, currencyAsCurrency, favoriteCryptoViewModel)
                }
            }
        }
    }
}