package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.getViewModel
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.components.ModalPage
import potel.nicolas.coinanalyzer.model.Language
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel
import potel.nicolas.coinanalyzer.ui.theme.applicationTheme

@Composable
fun LanguagesPage(
    navController : NavHostController,
    userPreferencesViewModel : UserPreferencesViewModel = getViewModel()
) {
    val currentLanguage by userPreferencesViewModel.currentLanguage.collectAsState()

    val borderRadius = 12.dp

    ModalPage(
        navController,
        stringResource(R.string.settings_language),
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(8.dp)
        ) {
            items(Language.entries.toTypedArray()) { language ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(borderRadius))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = true)
                        ) {
                            if (currentLanguage != language.symbol) {
                                userPreferencesViewModel.setLanguage(language.symbol)

                            }
                        }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = language.displayName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (currentLanguage == language.symbol) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected language icon",
                            modifier = Modifier.size(24.dp),
                            tint = applicationTheme.secondary,
                        )
                    }
                }
            }
        }
    }
}