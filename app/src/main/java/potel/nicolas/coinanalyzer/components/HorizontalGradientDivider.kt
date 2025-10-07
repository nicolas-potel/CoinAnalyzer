package potel.nicolas.coinanalyzer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import potel.nicolas.coinanalyzer.ui.theme.applicationTheme

@Composable
fun HorizontalGradientDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 4.dp,
    startColor: Color = applicationTheme.primary,
    endColor: Color = applicationTheme.secondary
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(startColor, endColor)
                )
            )
    )
}