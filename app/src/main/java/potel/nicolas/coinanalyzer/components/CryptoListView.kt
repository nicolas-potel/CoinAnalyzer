package potel.nicolas.coinanalyzer.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.favorites.FavoriteCrypto
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoViewModel
import potel.nicolas.coinanalyzer.api.CryptoData
import potel.nicolas.coinanalyzer.model.Currency
import potel.nicolas.coinanalyzer.model.TimeInterval
import potel.nicolas.coinanalyzer.api.getPercentChange
import potel.nicolas.coinanalyzer.ui.theme.applicationTheme

@Composable
fun CryptoListView(
    crypto : CryptoData,
    currency: Currency,
    timeInterval : TimeInterval,
    favoriteCryptoViewModel : FavoriteCryptoViewModel,
) {

    val iconButtonSize = 24.dp
    val quote = crypto.quote.values.first()
    val percentDiff = quote.getPercentChange(timeInterval)

    val percentDiffColor = if (percentDiff > 0)
        applicationTheme.increase
    else if (percentDiff < 0)
        applicationTheme.decrease
    else
        applicationTheme.fontSecondary

    val isFavorite by favoriteCryptoViewModel.isFavorite(crypto.id)
        .collectAsState(initial = false)


    val cryptoAsFavoriteCrypto = FavoriteCrypto.from(crypto)

    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = applicationTheme.fontSecondary,
                shape = RoundedCornerShape(42.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
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
                IconButton(
                    onClick = {
                        if (isFavorite) {
                            favoriteCryptoViewModel.removeFavorite(cryptoAsFavoriteCrypto)
                        } else {
                            favoriteCryptoViewModel.addFavorite(cryptoAsFavoriteCrypto)
                        }
                    },
                    modifier = Modifier
                        .size(iconButtonSize)
                        .align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = if (isFavorite) R.drawable.bookmark else R.drawable.bookmark_border
                        ),
                        contentDescription = "Add ${crypto.name} to favorites",
                        tint = applicationTheme.primary
                    )
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(iconButtonSize)
                        .align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share ${crypto.name}"
                    )
                }
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
        Spacer(modifier = Modifier.weight(1f))

        // Price
        Text(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            text = "${String.format("%.2f", quote.price)}${currency.displayName}",
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )

        // Increase / decrease icon
        Icon(
            imageVector = ImageVector.vectorResource(
                id = if (percentDiff >= 0) R.drawable.trending_up else R.drawable.trending_down
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