package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.components.SectionTitle
import potel.nicolas.coinanalyzer.config.Routes
import potel.nicolas.coinanalyzer.util.LanguageHandler


@Composable
fun SettingsPage(
    navController: NavHostController
) {

    val borderRadius = 12.dp

    val languageHandler by lazy {
        LanguageHandler()
    }

    val context = LocalContext.current.applicationContext

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
                    text = "not defined",
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
                    text = languageHandler.getLanguageCode(context).uppercase(),
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

        Row {
            Button(onClick = {
                languageHandler.setLanguage(context, "fr")
            }) {
                Text("French")
            }
            Button(onClick = {
                languageHandler.setLanguage(context, "en")
            }) {
                Text("English")
            }
        }
    }
}