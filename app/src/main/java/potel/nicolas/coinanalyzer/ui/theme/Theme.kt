package potel.nicolas.coinanalyzer.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

val applicationTheme = darkApplicationTheme;

@Composable
fun ApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = darkColorScheme,
        typography = Typography,
        content = content
    )
}