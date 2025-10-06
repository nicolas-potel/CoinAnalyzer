package potel.nicolas.coinanalyzer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import potel.nicolas.coinanalyzer.model.Crypto
import potel.nicolas.coinanalyzer.ui.theme.applicationTheme
import potel.nicolas.coinanalyzer.util.capitalizeFirstLetter

@Composable
fun SearchedCrypto(crypto : Crypto) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = applicationTheme.tertiary,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        AsyncImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = 8.dp)
                .size(30.dp)
                .aspectRatio(1f)
                .clip(CircleShape),
            model = crypto.imageLink,
            contentDescription = "Searched crypto ${crypto.name}"
        )
        Text(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            text = capitalizeFirstLetter(crypto.name)
        )
    }
}