package potel.nicolas.coinanalyzer.model

data class Crypto(
    val name: String,
    val symbol: String,
    val imageLink: String,
    val price: Double,
    val lastPrice: Double,
    val favorite: Boolean
)

// EXAMPLES TO BE REMOVED

val crypto1 = Crypto(
    "bitcoin",
    "btc",
    "https://coin-images.coingecko.com/coins/images/1/large/bitcoin.png",
    119321.5,
    125321.9,
    true
)
val crypto2 = Crypto(
    "ethereum",
    "eth",
    "https://coin-images.coingecko.com/coins/images/279/large/ethereum.png",
    1191.5,
    1111.5,
    false
)

val crypto3 = Crypto(
    "bittensor",
    "tao",
    "https://coin-images.coingecko.com/coins/images/28452/large/ARUsPeNQ_400x400.jpeg",
    119.5,
    121.2,
    true
)
