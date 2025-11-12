package potel.nicolas.coinanalyzer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    val data: List<CryptoData>
)

@JsonClass(generateAdapter = true)
data class CryptoData(
    val id: Int,
    val name: String,
    val symbol: String,
    @Json(name = "max_supply") val maxSupply: Double?,
    @Json(name = "cmc_rank") val rank: Int,
    val quote: Map<String, Quote>
)


@JsonClass(generateAdapter = true)
data class Quote(
    val price: Double,
    @Json(name = "percent_change_1h") val percentChange1h: Double,
    @Json(name = "percent_change_24h") val percentChange24h: Double,
    @Json(name = "percent_change_7d") val percentChange7d: Double,
    @Json(name = "percent_change_30d") val percentChange30d: Double,
    @Json(name = "market_cap") val marketCap: Double,
    @Json(name = "market_cap_dominance") val marketCapDominance: Double,
)