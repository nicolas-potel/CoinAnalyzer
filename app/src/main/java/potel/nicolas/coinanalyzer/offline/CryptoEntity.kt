package potel.nicolas.coinanalyzer.offline

import androidx.room.Entity
import androidx.room.PrimaryKey
import potel.nicolas.coinanalyzer.api.CryptoData
import potel.nicolas.coinanalyzer.model.CryptoItem

@Entity(tableName = "crypto_entity")
data class CryptoEntity(
    @PrimaryKey override val id: Int,
    override val name: String,
    override  val symbol: String,
    val maxSupply: Double?,
    val rank: Int,

    val quoteJson: String
) : CryptoItem

fun CryptoEntity.toCryptoData(converter: QuoteMapConverter): CryptoData {
    return CryptoData(
        id = id,
        name = name,
        symbol = symbol,
        maxSupply = maxSupply,
        rank = rank,
        quote = converter.toMap(quoteJson)
    )
}