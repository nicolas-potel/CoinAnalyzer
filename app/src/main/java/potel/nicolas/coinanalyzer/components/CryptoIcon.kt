package potel.nicolas.coinanalyzer.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.model.CryptoData

@Composable
fun CryptoIcon(
    crypto : CryptoData,
    size : Dp = 48.dp
) {
    val iconURL = "https://s2.coinmarketcap.com/static/img/coins/64x64/${crypto.id}.png"

    AsyncImage(
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(end = 10.dp)
            .size(size)
            .aspectRatio(1f)
            .clip(CircleShape),
        model = iconURL,
        contentDescription = "Crypto Icon ${crypto.symbol}",
        placeholder = painterResource(R.drawable.icon_placeholder),
        error = painterResource(R.drawable.icon_placeholder)
    )
}