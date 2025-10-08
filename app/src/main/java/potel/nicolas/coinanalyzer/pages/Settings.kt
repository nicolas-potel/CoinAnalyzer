package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.getViewModel
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.components.SectionTitle
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel

@Composable
fun SettingsPage(
    userPreferencesViewModel : UserPreferencesViewModel = getViewModel()
) {
    val isListViewEnabled by userPreferencesViewModel.isListViewEnabled.collectAsState()

    Column {
        SectionTitle(stringResource(id = R.string.page_settings))
        if (isListViewEnabled) {
            Text("ok")
        } else {
            Text("pas ok")
        }
        Button(
            onClick = {
                userPreferencesViewModel.toggleListView()
            }
        ) {
            Text("toggle")
        }
    }
}