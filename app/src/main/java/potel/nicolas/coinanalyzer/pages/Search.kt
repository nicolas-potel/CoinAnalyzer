package potel.nicolas.coinanalyzer.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.components.SearchedCrypto
import potel.nicolas.coinanalyzer.components.SectionTitle
import potel.nicolas.coinanalyzer.config.Routes
import potel.nicolas.coinanalyzer.model.Crypto

@Composable
fun SearchPage(navController: NavHostController) {
    val crypto1 = Crypto(
        "bitcoin",
        "btc",
        "https://coin-images.coingecko.com/coins/images/1/large/bitcoin.png",
        119.5
    )

    val crypto2 = Crypto(
        "ethereum",
        "eth",
        "https://coin-images.coingecko.com/coins/images/279/large/ethereum.png",
        119.5
    )

    val crypto3 = Crypto(
        "bittensor",
        "tao",
        "https://coin-images.coingecko.com/coins/images/28452/large/ARUsPeNQ_400x400.jpeg",
        119.5
    )

    Column {
        SectionTitle(stringResource(id = R.string.page_search))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SearchedCrypto(crypto1)
            SearchedCrypto(crypto2)
            SearchedCrypto(crypto1)
            SearchedCrypto(crypto2)
            SearchedCrypto(crypto3)
        }
    }
}