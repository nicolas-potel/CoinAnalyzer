package potel.nicolas.coinanalyzer.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import potel.nicolas.coinanalyzer.ui.theme.applicationTheme

@Composable
fun TimeIntervalSwitcher(
    selectedInterval: String, // "1h", "24h", "7d", "30d"
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val options = listOf("1h", "24h", "7d", "30d")
    val selectedIndex = options.indexOf(selectedInterval)

    BoxWithConstraints(
        modifier = modifier
            .border(
                width = 1.dp,
                color = applicationTheme.fontSecondary,
                shape = RoundedCornerShape(42.dp)
            )
            .height(48.dp)
            .clip(RoundedCornerShape(22.dp))
    ) {
        val optionWidth = maxWidth / options.size

        val offset by animateDpAsState(
            targetValue = selectedIndex * optionWidth,
            animationSpec = tween(durationMillis = 250)
        )

        Box(
            modifier = Modifier
                .offset(x = offset)
                .fillMaxHeight()
                .width(optionWidth)
                .padding(7.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(applicationTheme.primary)
        )

        Row(modifier = Modifier.fillMaxSize()) {
            options.forEach { interval ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onSelect(interval) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = interval,
                        color = applicationTheme.font,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
