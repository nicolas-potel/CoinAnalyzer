package potel.nicolas.coinanalyzer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import potel.nicolas.coinanalyzer.model.CryptoData
import potel.nicolas.coinanalyzer.ui.theme.applicationTheme
import potel.nicolas.coinanalyzer.util.capitalizeFirstLetter

@Composable
fun SearchedCrypto(crypto : CryptoData) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = applicationTheme.tertiary,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        CryptoIcon(crypto, 30.dp)
        Text(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            text = capitalizeFirstLetter(crypto.symbol)
        )
    }
}