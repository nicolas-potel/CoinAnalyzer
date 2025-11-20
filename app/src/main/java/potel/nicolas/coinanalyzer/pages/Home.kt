package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.api.CryptoViewModel
import potel.nicolas.coinanalyzer.components.CryptoGridView
import potel.nicolas.coinanalyzer.components.CryptoListView
import potel.nicolas.coinanalyzer.components.ErrorMessage
import potel.nicolas.coinanalyzer.components.SectionTitle
import potel.nicolas.coinanalyzer.components.TimeIntervalSwitcher
import potel.nicolas.coinanalyzer.config.Routes
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoViewModel
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel

@Composable
fun HomePage(
    navController : NavHostController,
    userPreferencesViewModel: UserPreferencesViewModel,
    cryptoViewModel: CryptoViewModel,
    favoriteCryptoViewModel: FavoriteCryptoViewModel
) {
    val displayNbElements = 4

    val selectedCurrency by userPreferencesViewModel.currency.collectAsState()
    val selectedTimeInterval by userPreferencesViewModel.timeInterval.collectAsState()
    val isListView by userPreferencesViewModel.isListViewEnabled.collectAsState()

    val favoriteCryptos by favoriteCryptoViewModel.favorites.collectAsState()
    val favoriteIds = favoriteCryptos.map { it.id }.toSet()
    val cryptos by cryptoViewModel.cryptos.collectAsState()
    val favoriteCryptosAsCryptos = cryptos.filter { crypto ->
        favoriteIds.contains(crypto.id)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TimeIntervalSwitcher(
            selectedInterval = selectedTimeInterval,
            onSelect = {
                userPreferencesViewModel.setTimeInterval(it)
            }
        )
        SectionTitle(stringResource(id = R.string.home_favorites))

        // Display some favorite cryptos if possible
        if (favoriteCryptosAsCryptos.isEmpty()){
            ErrorMessage(stringResource(R.string.favorites_no_data))
        } else {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                favoriteCryptosAsCryptos
                    .take(displayNbElements)
                    .forEach {
                        if (isListView) {
                            CryptoListView(
                                it,
                                selectedCurrency,
                                selectedTimeInterval,
                                favoriteCryptoViewModel
                            )
                        } else {
                            CryptoGridView(
                                it,
                                selectedCurrency,
                                selectedTimeInterval,
                                favoriteCryptoViewModel
                            )
                        }
                    }
            }
            if (favoriteCryptosAsCryptos.size > displayNbElements) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { navController.navigate(Routes.FAVORITES) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.home_see_more),
                        fontSize = 20.sp
                    )
                }
            }
        }

        // We display some random cryptos here
        SectionTitle(stringResource(id = R.string.home_coins))
        if (cryptos.isEmpty()) {
            ErrorMessage(stringResource(R.string.coins_no_data))
        } else {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                cryptos
                    .take(displayNbElements)
                    .forEach {
                        if (isListView) {
                            CryptoListView(
                                it,
                                selectedCurrency,
                                selectedTimeInterval,
                                favoriteCryptoViewModel
                            )
                        } else {
                            CryptoGridView(
                                it,
                                selectedCurrency,
                                selectedTimeInterval,
                                favoriteCryptoViewModel
                            )
                        }
                    }
            }
            if (cryptos.size > displayNbElements) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { navController.navigate(Routes.COINS) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.home_see_more),
                        fontSize = 20.sp
                    )
                }
            }
        }

    }
}