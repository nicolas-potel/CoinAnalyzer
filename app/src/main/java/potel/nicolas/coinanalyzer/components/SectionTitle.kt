package potel.nicolas.coinanalyzer.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import potel.nicolas.coinanalyzer.ui.theme.applicationTheme

/**
 * Displays specified title with default applications colors.
 */
@Composable
fun SectionTitle(content: String) {
    Text(
        text = "$content   ",
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .drawBehind {
                val gradient = Brush.horizontalGradient(
                    colors = listOf(applicationTheme.primary, applicationTheme.secondary)
                )
                drawLine(
                    brush = gradient,
                    strokeWidth = 4.dp.toPx(),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                )
            }
    )
}