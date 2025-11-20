package potel.nicolas.coinanalyzer.recentsearch

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import potel.nicolas.coinanalyzer.model.CryptoItem

@Entity(tableName = "recentSearches")
@JsonClass(generateAdapter = true)
data class RecentSearch(

    @PrimaryKey override val id: Int,
    override val name: String,
    override val symbol: String,
    val timestamp: Long = System.currentTimeMillis()

) : CryptoItem {

    companion object {
        fun from(crypto: CryptoItem): RecentSearch {
            return RecentSearch(
                crypto.id,
                crypto.name,
                crypto.symbol
            )
        }
    }

}