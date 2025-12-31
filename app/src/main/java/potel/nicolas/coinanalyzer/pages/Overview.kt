package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.api.CryptoViewModel
import potel.nicolas.coinanalyzer.api.getPercentChange
import potel.nicolas.coinanalyzer.components.CryptoIcon
import potel.nicolas.coinanalyzer.components.SectionTitle
import potel.nicolas.coinanalyzer.components.TimeIntervalSwitcher
import potel.nicolas.coinanalyzer.favorites.FavoriteCrypto
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoViewModel
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel
import potel.nicolas.coinanalyzer.ui.theme.applicationTheme
import potel.nicolas.coinanalyzer.util.ViewModels
import potel.nicolas.coinanalyzer.util.capitalizeFirstLetter

@Composable
fun OverviewPage(
    cryptoViewModel: CryptoViewModel = ViewModels.cryptoViewModel,
    favoriteCryptoViewModel: FavoriteCryptoViewModel = ViewModels.favoriteCryptoViewModel,
    userPreferencesViewModel: UserPreferencesViewModel = ViewModels.userPreferencesViewModel
) {
    val iconButtonSize = 26.dp

    val cryptoDetails by cryptoViewModel.selectedCryptoDetails.collectAsState()
    val selectedCrypto by cryptoViewModel.selectedCrypto.collectAsState()
    val selectedCurrency by userPreferencesViewModel.currency.collectAsState()
    val timeInterval by userPreferencesViewModel.timeInterval.collectAsState()

    val isFavorite: Boolean = if (selectedCrypto != null) {
        favoriteCryptoViewModel.isFavorite(selectedCrypto!!.id)
            .collectAsState(initial = false)
            .value
    } else {
        false
    }

    val quote = selectedCrypto!!.quote.values.first()
    val percentDiff = quote.getPercentChange(timeInterval)

    val percentDiffColor = if (percentDiff > 0)
        applicationTheme.increase
    else if (percentDiff < 0)
        applicationTheme.decrease
    else
        applicationTheme.fontSecondary

    val selectedCryptoAsFavoriteCrypto : FavoriteCrypto? = selectedCrypto?.let {
        FavoriteCrypto.from(it)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row {
            SectionTitle(stringResource(id = R.string.overview_title))
            Spacer(Modifier.weight(1f))
            IconButton(
                onClick = {
                    if (isFavorite) {
                        favoriteCryptoViewModel.removeFavorite(FavoriteCrypto.from(selectedCrypto!!))
                    } else {
                        favoriteCryptoViewModel.addFavorite(FavoriteCrypto.from(selectedCrypto!!))
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
                    contentDescription = "Add selected crypto to favorites",
                    tint = applicationTheme.primary
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            )  {
                CryptoIcon(selectedCrypto!!,64.dp)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        text = "${capitalizeFirstLetter(selectedCrypto!!.name)} (${selectedCrypto!!.symbol})"
                    )
                    Spacer(Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                color = applicationTheme.tertiary,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 4.dp, horizontal = 6.dp)
                    ) {
                        Text(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFACACAC),
                            text = "#${selectedCrypto!!.rank}"
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        text = "${String.format("%.2f", quote.price)}${selectedCurrency.displayName}"
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = if (percentDiff >= 0) R.drawable.trending_up else R.drawable.trending_down
                        ),
                        tint = percentDiffColor,
                        contentDescription = "Increase/decrease icon displayer",
                        modifier = Modifier
                            .size(32.dp)
                            .padding(start = 4.dp)
                            .align(Alignment.CenterVertically)
                    )
                }

                Text(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = percentDiffColor,
                    text = (if (percentDiff >= 0) "+" else "")
                            + String.format("%.3f", percentDiff)
                            + "%"
                )

                TimeIntervalSwitcher(
                    selectedInterval = timeInterval,
                    onSelect = {
                        userPreferencesViewModel.setTimeInterval(it)
                    }
                )

                Text(if (cryptoDetails != null) cryptoDetails!!.description else "")
            }
        }
    }
}