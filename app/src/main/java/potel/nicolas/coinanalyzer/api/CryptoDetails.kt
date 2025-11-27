package potel.nicolas.coinanalyzer.api

import com.squareup.moshi.JsonClass
import potel.nicolas.coinanalyzer.model.CryptoItem

@JsonClass(generateAdapter = true)
data class CryptoDetailsResponse(
    val data: Map<String, CryptoDetails>
)

@JsonClass(generateAdapter = true)
data class CryptoDetails(
    override val id: Int,
    override val name: String,
    override val symbol: String,
    val description: String
) : CryptoItem