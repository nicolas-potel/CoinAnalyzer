package potel.nicolas.coinanalyzer.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.first
import java.io.InputStream
import java.io.OutputStream

@JsonClass(generateAdapter = true)
data class UserPreferences(
    val currency : String = UserPreferencesDefaultValues.currency,
    val listViewEnabled : Boolean = UserPreferencesDefaultValues.listViewEnabled,
    val timeInterval : String = UserPreferencesDefaultValues.timeInterval,
    val filter : String = UserPreferencesDefaultValues.filter
)

suspend fun Context.readUserPreferences(): UserPreferences {
    val prefs = userPreferencesDataStore.data.first()
    return UserPreferences(
        listViewEnabled = prefs[UserPreferencesKeys.LIST_VIEW_ENABLED] ?: UserPreferencesDefaultValues.listViewEnabled,
        currency = prefs[UserPreferencesKeys.CURRENCY] ?: UserPreferencesDefaultValues.currency,
        timeInterval = prefs[UserPreferencesKeys.TIME_INTERVAL] ?: UserPreferencesDefaultValues.timeInterval,
        filter = prefs[UserPreferencesKeys.FILTER] ?: UserPreferencesDefaultValues.filter
    )
}

suspend fun Context.saveUserPreferences(prefs: UserPreferences) {
    userPreferencesDataStore.edit { data ->
        data[UserPreferencesKeys.LIST_VIEW_ENABLED] = prefs.listViewEnabled
        data[UserPreferencesKeys.CURRENCY] = prefs.currency
        data[UserPreferencesKeys.TIME_INTERVAL] = prefs.timeInterval
        data[UserPreferencesKeys.FILTER] = prefs.filter
    }
}

suspend fun Context.exportUserPreferences(outputStream: OutputStream) {
    val prefs = readUserPreferences()
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val adapter = moshi.adapter(UserPreferences::class.java)
    val json = adapter.toJson(prefs)

    outputStream.bufferedWriter().use { it.write(json) }
}


suspend fun Context.importUserPreferences(inputStream: InputStream) {
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val adapter = moshi.adapter(UserPreferences::class.java)

    val prefs = inputStream.bufferedReader().use { adapter.fromJson(it.readText()) }
    prefs?.let { saveUserPreferences(it) }
}