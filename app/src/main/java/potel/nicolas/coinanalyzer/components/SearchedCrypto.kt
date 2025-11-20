package potel.nicolas.coinanalyzer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import potel.nicolas.coinanalyzer.model.CryptoItem
import potel.nicolas.coinanalyzer.recentsearch.RecentSearchViewModel
import potel.nicolas.coinanalyzer.ui.theme.applicationTheme
import potel.nicolas.coinanalyzer.util.capitalizeFirstLetter

@Composable
fun SearchedCrypto(
    crypto : CryptoItem,
    recentSearchViewModel: RecentSearchViewModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = applicationTheme.tertiary,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true)
            ) { recentSearchViewModel.addRecentSearch(crypto) }
    ) {
        CryptoIcon(crypto, 30.dp)
        Text(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            text = capitalizeFirstLetter(crypto.symbol)
        )
    }
}