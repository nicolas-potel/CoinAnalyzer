package potel.nicolas.coinanalyzer.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val colorScheme = darkColorScheme(
    primary = darkApplicationTheme.primary,

    secondary = darkApplicationTheme.secondary,
    tertiary = darkApplicationTheme.tertiary,

    background = darkApplicationTheme.background
)

@Composable
fun ApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}