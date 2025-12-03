package potel.nicolas.coinanalyzer.api

import com.squareup.moshi.JsonClass
import potel.nicolas.coinanalyzer.model.CryptoItem
import java.time.Instant

@JsonClass(generateAdapter = true)
data class CryptoDetailsResponse(
    val data: CryptoDetailsData
)

@JsonClass(generateAdapter = true)
data class CryptoDetailsData(
    override val id: Int,
    override val name: String,
    override val symbol: String,
    val quotes : List<TimedQuote>
) : CryptoItem

@JsonClass(generateAdapter = true)
data class TimedQuote(
    val timestamp : Instant,
    val quote : Quote,
)