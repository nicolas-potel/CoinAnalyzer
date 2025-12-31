package potel.nicolas.coinanalyzer.pages

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import potel.nicolas.coinanalyzer.MainApplication
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.components.SectionTitle
import potel.nicolas.coinanalyzer.config.Routes
import potel.nicolas.coinanalyzer.preferences.LanguageViewModel
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel
import potel.nicolas.coinanalyzer.preferences.exportUserPreferences
import potel.nicolas.coinanalyzer.preferences.importUserPreferences
import potel.nicolas.coinanalyzer.util.ViewModels
import potel.nicolas.coinanalyzer.util.displayToastMessage


@Composable
fun SettingsPage(
    navController: NavHostController,
    userPreferencesViewModel: UserPreferencesViewModel = ViewModels.userPreferencesViewModel,
    languageViewModel: LanguageViewModel = ViewModels.languageViewModel
) {

    val borderRadius = 12.dp

    val context = LocalContext.current
    val app = context.applicationContext as MainApplication
    val scope = rememberCoroutineScope()

    val currency by userPreferencesViewModel.currency.collectAsState()

    val defaultFileName = "user_preferences.json"
    val exportLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/json")
    ) { uri ->
        uri?.let { destinationUri ->
            scope.launch {
                context.contentResolver.openOutputStream(destinationUri)?.use { outputStream ->
                    context.exportUserPreferences(outputStream, app.favoriteCryptoRepository)
                    displayToastMessage(context, context.getString(R.string.toast_export_config_success))
                }
            }
        }
    }

    val importLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let { sourceUri ->
            scope.launch {
                context.contentResolver.openInputStream(sourceUri)?.use { inputStream ->
                    context.importUserPreferences(inputStream, app.favoriteCryptoRepository)
                    displayToastMessage(context, context.getString(R.string.toast_import_config_success))
                }
            }
        }
    }

    Column {
        SectionTitle(stringResource(id = R.string.page_settings))

        // Currency
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.settings_currency),
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(borderRadius))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true)
                    ) {
                        navController.navigate(Routes.CURRENCIES)
                    }
                    .padding(horizontal = 8.dp)
            ){
                Text(
                    text = currency.symbol,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.keyboard_arrow_right),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "arrow"
                )
            }
        }

        // Language
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.settings_language),
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(borderRadius))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true)
                    ) {
                        navController.navigate(Routes.LANGUAGES)
                    }
                    .padding(horizontal = 8.dp)
            ){
                Text(
                    text = languageViewModel.getLanguageCode(context).uppercase(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.keyboard_arrow_right),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "arrow"
                )
            }
        }

        // Filter
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.settings_filter),
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(borderRadius))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true)
                    ) {
                        navController.navigate(Routes.FILTERS)
                    }
                    .padding(horizontal = 8.dp)
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.keyboard_arrow_right),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "arrow"
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                exportLauncher.launch(defaultFileName)
            }
        ) {
            Text(stringResource(R.string.settings_export_configuration))
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                importLauncher.launch(arrayOf("application/json"))
            }
        ) {
            Text(stringResource(R.string.settings_import_configuration))
        }
    }
}