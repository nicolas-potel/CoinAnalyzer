package potel.nicolas.coinanalyzer.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.first
import potel.nicolas.coinanalyzer.favorites.FavoriteCrypto
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoRepository
import java.io.InputStream
import java.io.OutputStream

@JsonClass(generateAdapter = true)
data class UserPreferencesExport(
    val preferences: UserPreferences,
    val favorites: List<FavoriteCrypto> = emptyList()
)

/**
 * Returns the current user preferences.
 */
suspend fun Context.readUserPreferences(): UserPreferences {
    val prefs = userPreferencesDataStore.data.first()
    return UserPreferences(
        listViewEnabled = prefs[UserPreferencesKeys.LIST_VIEW_ENABLED] ?: UserPreferencesDefaultValues.listViewEnabled,
        currency = prefs[UserPreferencesKeys.CURRENCY] ?: UserPreferencesDefaultValues.currency,
        timeInterval = prefs[UserPreferencesKeys.TIME_INTERVAL] ?: UserPreferencesDefaultValues.timeInterval,
        filter = prefs[UserPreferencesKeys.FILTER] ?: UserPreferencesDefaultValues.filter
    )
}

/**
 * Saves the current user preferences.
 *
 * @param prefs The user preferences to be saved.
 */
suspend fun Context.saveUserPreferences(prefs: UserPreferences) {
    userPreferencesDataStore.edit { data ->
        data[UserPreferencesKeys.LIST_VIEW_ENABLED] = prefs.listViewEnabled
        data[UserPreferencesKeys.CURRENCY] = prefs.currency
        data[UserPreferencesKeys.TIME_INTERVAL] = prefs.timeInterval
        data[UserPreferencesKeys.FILTER] = prefs.filter
    }
}

/**
 * Exports the current user preferences to the given output stream.
 *
 * @param outputStream The output stream to be exported to.
 */
suspend fun Context.exportUserPreferences(
    outputStream : OutputStream,
    favoriteCryptoRepository: FavoriteCryptoRepository
) {
    val prefs = readUserPreferences()
    val favorites = favoriteCryptoRepository.getAllFavorites()

    val exportData = UserPreferencesExport(
        preferences = prefs,
        favorites = favorites
    )

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val adapter = moshi.adapter(UserPreferencesExport::class.java)
    val json = adapter.toJson(exportData)

    outputStream.bufferedWriter().use { it.write(json) }
}

/**
 * Imports the user preferences from the given input stream.
 *
 * @param inputStream The input stream to be imported from.
 */
suspend fun Context.importUserPreferences(
    inputStream: InputStream,
    favoriteCryptoRepository: FavoriteCryptoRepository
) {
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val adapter = moshi.adapter(UserPreferencesExport::class.java)

    val exportData = inputStream.bufferedReader().use { adapter.fromJson(it.readText()) }
    exportData?.let {
        saveUserPreferences(it.preferences)
        favoriteCryptoRepository.insertAll(it.favorites)
    }
}