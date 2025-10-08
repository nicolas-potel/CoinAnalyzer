package potel.nicolas.coinanalyzer.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(private val context: Context) {

    val isListViewEnabled = context.userPreferencesDataStore.data.map { preferences ->
        preferences[UserPreferencesKeys.LIST_VIEW_ENABLED] ?: true
    }

    suspend fun setListViewEnabled(enabled: Boolean) {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[UserPreferencesKeys.LIST_VIEW_ENABLED] = enabled
        }
    }
}