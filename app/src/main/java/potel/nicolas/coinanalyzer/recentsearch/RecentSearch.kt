package potel.nicolas.coinanalyzer.recentsearch

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import potel.nicolas.coinanalyzer.api.CryptoData

@Entity(tableName = "recentSearches")
@JsonClass(generateAdapter = true)
data class RecentSearch(
    @PrimaryKey val id: Int,
    val name: String,
    val symbol: String,
    val timestamp: Long = System.currentTimeMillis()
) {
    companion object {
        fun from(crypto: CryptoData): RecentSearch {
            return RecentSearch(
                crypto.id,
                crypto.name,
                crypto.symbol
            )
        }
    }
}