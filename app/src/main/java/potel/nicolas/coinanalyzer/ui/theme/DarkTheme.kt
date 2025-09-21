package potel.nicolas.coinanalyzer.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

val darkApplicationTheme = ApplicationTheme(
    background = Color(0xFF111315),
    font = Color(0xFFFFFFFF),
    fontSecondary = Color(0xFF333536),

    increase = Color(0xFF007C38),
    decrease = Color(0xFFC20306),

    primary = Color(0xFF9338F5),
    secondary = Color(0xFFFC17D4),
    tertiary = Color(0xFF252728)
)

val darkColorScheme = darkColorScheme(
    primary = darkApplicationTheme.primary,

    secondary = darkApplicationTheme.secondary,
    tertiary = darkApplicationTheme.tertiary,

    background = darkApplicationTheme.background,
    onPrimary = darkApplicationTheme.font
)