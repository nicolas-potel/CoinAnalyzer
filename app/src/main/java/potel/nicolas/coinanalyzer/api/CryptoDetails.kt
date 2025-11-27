package potel.nicolas.coinanalyzer.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CryptoDetailsResponse(
    val data: Map<String, CryptoDetails>
)

@JsonClass(generateAdapter = true)
data class CryptoDetails(
    val symbol: String,
    val description: String
)