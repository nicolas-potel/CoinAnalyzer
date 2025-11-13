package potel.nicolas.coinanalyzer.favorites

import androidx.room.Entity
import androidx.room.PrimaryKey
import potel.nicolas.coinanalyzer.model.CryptoData

@Entity(tableName = "favorites")
data class FavoriteCrypto(
    @PrimaryKey val id: Int,
) {
    companion object {
        fun from(crypto: CryptoData): FavoriteCrypto {
            return FavoriteCrypto(crypto.id)
        }
    }
}