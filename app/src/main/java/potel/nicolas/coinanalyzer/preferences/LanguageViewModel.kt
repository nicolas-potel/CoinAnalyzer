package potel.nicolas.coinanalyzer.preferences

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LanguageViewModel() : ViewModel() {

    private val _shouldRestartActivity = MutableStateFlow(false)
    val shouldRestartActivity: StateFlow<Boolean> = _shouldRestartActivity

    fun setLanguage(context: Context, languageCode: String) {
        viewModelScope.launch {
            val prefs = context.getSharedPreferences("language_prefs", Context.MODE_PRIVATE)
            prefs.edit { putString("language", languageCode) }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.applicationContext.getSystemService(LocaleManager::class.java)
                    .applicationLocales = LocaleList.forLanguageTags(languageCode)
            } else {
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
            }

            _shouldRestartActivity.value = true
        }
    }

    fun getLanguageCode(context: Context): String {
        val prefs = context.getSharedPreferences("language_prefs", Context.MODE_PRIVATE)
        val saved = prefs.getString("language", null) ?: "en"
        return saved
    }

    fun consumeRestartEvent() {
        _shouldRestartActivity.value = false
    }
}