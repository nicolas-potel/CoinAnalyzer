package potel.nicolas.coinanalyzer.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.model.CryptoData
import potel.nicolas.coinanalyzer.ui.theme.applicationTheme

@Composable
fun CryptoGridView(crypto : CryptoData) {

    val quote = crypto.quote["USD"]!!
    val percentDiff = quote.percentChange1h

    val percentDiffColor = if (percentDiff >= 0)
        applicationTheme.increase
    else
        applicationTheme.decrease

    Column(
        modifier = Modifier
            .fillMaxWidth(0.488f)
            .aspectRatio(1.4f)
            .border(
                width = 1.dp,
                color = applicationTheme.fontSecondary,
                shape = RoundedCornerShape(42.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxHeight(0.6f)
        ) {
            CryptoIcon(crypto)

            Column {

                Row {
                    // Crypto Symbol
                    Text(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        text = crypto.symbol.uppercase(),
                        modifier = Modifier.padding(end = 4.dp)
                    )

                    // Actions (favorite / share)
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = if (true) R.drawable.bookmark else R.drawable.bookmark_border
                        ),
                        contentDescription = "Add ${crypto.name} to favorites",
                        tint = applicationTheme.primary,
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share ${crypto.name}",
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.CenterVertically)
                    )
                }

                // Price diff in percent
                Text(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = percentDiffColor,
                    text = (if (percentDiff >= 0) "+" else "")
                            + String.format("%.3f", percentDiff)
                            + "%"
                )

            }
        }

        Row(
            modifier = Modifier.fillMaxHeight()
        ) {
            // Price
            Text(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                text = "${quote.price}$",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

            // Increase / decrease icon
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = if (true) R.drawable.trending_up else R.drawable.trending_down
                ),
                tint = percentDiffColor,
                contentDescription = "Increase/decrease icon displayer",
                modifier = Modifier
                    .size(28.dp)
                    .padding(start = 4.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}