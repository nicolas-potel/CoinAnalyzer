package potel.nicolas.coinanalyzer.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import potel.nicolas.coinanalyzer.model.Currency
import potel.nicolas.coinanalyzer.model.TimeInterval

val Context.userPreferencesDataStore by preferencesDataStore(name = "user_preferences")

object UserPreferencesKeys {
    val LIST_VIEW_ENABLED = booleanPreferencesKey("list_view_enabled")
    val CURRENCY = stringPreferencesKey("currency")
    val TIME_INTERVAL = intPreferencesKey("time_interval")
}

object UserPreferencesDefaultValues {
    val currency = Currency.USD.symbol
    val listViewEnabled = true
    val timeInterval = TimeInterval.DAY.displayName
}