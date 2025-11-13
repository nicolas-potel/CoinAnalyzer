package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.api.CryptoViewModel
import potel.nicolas.coinanalyzer.components.SectionTitle
import potel.nicolas.coinanalyzer.components.TimeIntervalSwitcher
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoViewModel
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel

@Composable
fun HomePage(
    userPreferencesViewModel: UserPreferencesViewModel,
    cryptoViewModel: CryptoViewModel,
    favoriteCryptoViewModel: FavoriteCryptoViewModel
) {
    var selectedInterval by remember { mutableStateOf("1h") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SectionTitle(stringResource(id = R.string.page_home))
        TimeIntervalSwitcher(
            selectedInterval = selectedInterval,
            onSelect = { selectedInterval = it }
        )
    }
}