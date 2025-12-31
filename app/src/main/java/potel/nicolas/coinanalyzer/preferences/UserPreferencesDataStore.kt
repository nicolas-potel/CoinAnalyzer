package potel.nicolas.coinanalyzer.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import potel.nicolas.coinanalyzer.model.Currency
import potel.nicolas.coinanalyzer.model.Filter
import potel.nicolas.coinanalyzer.model.TimeInterval

val Context.userPreferencesDataStore by preferencesDataStore(name = "user_preferences")

object UserPreferencesKeys {
    val CURRENCY = stringPreferencesKey("currency")
    val TIME_INTERVAL = stringPreferencesKey("time_interval")
    val FILTER = stringPreferencesKey("filter")
}

object UserPreferencesDefaultValues {
    val currency = Currency.USD.symbol
    val timeInterval = TimeInterval.DAY.id
    val filter = Filter.RANK_DESC.id
}