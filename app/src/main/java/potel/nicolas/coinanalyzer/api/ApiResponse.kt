package potel.nicolas.coinanalyzer.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import potel.nicolas.coinanalyzer.model.CryptoItem
import potel.nicolas.coinanalyzer.model.TimeInterval

@JsonClass(generateAdapter = true)
data class ApiResponse(
    val data: List<CryptoData>
)

@JsonClass(generateAdapter = true)
data class CryptoData(
    override val id: Int,
    override val name: String,
    override val symbol: String,
    @Json(name = "max_supply") val maxSupply: Double?,
    @Json(name = "cmc_rank") val rank: Int,
    val quote: Map<String, Quote>
) : CryptoItem


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

fun Quote.getPercentChange(interval: TimeInterval): Double {
    return when(interval) {
        TimeInterval.HOUR -> percentChange1h
        TimeInterval.DAY -> percentChange24h
        TimeInterval.WEEK -> percentChange7d
        TimeInterval.MONTH -> percentChange30d
    }
}