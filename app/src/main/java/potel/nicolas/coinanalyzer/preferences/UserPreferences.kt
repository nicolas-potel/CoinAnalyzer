package potel.nicolas.coinanalyzer.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.first
import java.io.InputStream
import java.io.OutputStream

/**
 * Data class representing the user preferences.
 */
@JsonClass(generateAdapter = true)
data class UserPreferences(
    val currency : String = UserPreferencesDefaultValues.currency,
    val timeInterval : String = UserPreferencesDefaultValues.timeInterval,
    val filter : String = UserPreferencesDefaultValues.filter
)