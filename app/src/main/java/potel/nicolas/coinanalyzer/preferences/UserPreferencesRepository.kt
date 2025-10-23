package potel.nicolas.coinanalyzer.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(
    private val context: Context
) {

    /**
     * List view handling.
     */
    val isListViewEnabled = context.userPreferencesDataStore.data.map { preferences ->
        preferences[UserPreferencesKeys.LIST_VIEW_ENABLED] ?: UserPreferencesDefaultValues.listViewEnabled
    }

    suspend fun setListViewEnabled(enabled: Boolean) {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[UserPreferencesKeys.LIST_VIEW_ENABLED] = enabled
        }
    }

    /**
     * Currency handling.
     */
    val currency = context.userPreferencesDataStore.data.map { preferences ->
        preferences[UserPreferencesKeys.CURRENCY] ?: UserPreferencesDefaultValues.currency
    }

    suspend fun setCurrency(newCurrency: String) {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[UserPreferencesKeys.CURRENCY] = newCurrency
        }
    }
}