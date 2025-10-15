package potel.nicolas.coinanalyzer.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import potel.nicolas.coinanalyzer.model.Currency
import potel.nicolas.coinanalyzer.model.Language

val Context.userPreferencesDataStore by preferencesDataStore(name = "user_preferences")

object UserPreferencesKeys {
    val LIST_VIEW_ENABLED = booleanPreferencesKey("list_view_enabled")
    val CURRENCY = stringPreferencesKey("currency")
    val LANGUAGE = stringPreferencesKey("language")
}

object UserPreferencesDefaultValues {
    val language = Language.EN.symbol
    val currency = Currency.USD.displayName
    val listViewEnabled = true
}