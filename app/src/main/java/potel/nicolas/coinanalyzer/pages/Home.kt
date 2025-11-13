package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.api.CryptoViewModel
import potel.nicolas.coinanalyzer.components.SectionTitle
import potel.nicolas.coinanalyzer.components.TimeIntervalSwitcher
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoViewModel
import potel.nicolas.coinanalyzer.model.TimeInterval
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel

@Composable
fun HomePage(
    userPreferencesViewModel: UserPreferencesViewModel,
    cryptoViewModel: CryptoViewModel,
    favoriteCryptoViewModel: FavoriteCryptoViewModel
) {
    val selectedInterval by userPreferencesViewModel.timeInterval.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TimeIntervalSwitcher(
            selectedInterval = TimeInterval.from(selectedInterval),
            onSelect = {
                userPreferencesViewModel.setTimeInterval(it)
            }
        )
        SectionTitle(stringResource(id = R.string.page_home))
    }
}