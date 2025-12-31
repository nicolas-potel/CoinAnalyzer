package potel.nicolas.coinanalyzer.offline

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import potel.nicolas.coinanalyzer.api.Quote

/**
 * Converts a Map<String, Quote> to a JSON string and vice versa.
 */
class QuoteMapConverter {

    private val moshi = Moshi.Builder().build()
    private val type =
        Types.newParameterizedType(Map::class.java, String::class.java, Quote::class.java)
    private val adapter: JsonAdapter<Map<String, Quote>> = moshi.adapter(type)

    @TypeConverter
    fun fromMap(map: Map<String, Quote>): String {
        return adapter.toJson(map)
    }

    @TypeConverter
    fun toMap(json: String): Map<String, Quote> {
        return adapter.fromJson(json) ?: emptyMap()
    }
}