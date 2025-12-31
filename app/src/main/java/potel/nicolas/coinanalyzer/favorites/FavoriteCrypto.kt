package potel.nicolas.coinanalyzer.favorites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import potel.nicolas.coinanalyzer.api.CryptoData
import potel.nicolas.coinanalyzer.model.CryptoItem

/**
 * Favorite crypto entity.
 */
@Entity(tableName = "favorites")
@JsonClass(generateAdapter = true)
data class FavoriteCrypto(
    @PrimaryKey val id: Int,
) {
    companion object {
        fun from(crypto: CryptoItem): FavoriteCrypto {
            return FavoriteCrypto(crypto.id)
        }
    }
}