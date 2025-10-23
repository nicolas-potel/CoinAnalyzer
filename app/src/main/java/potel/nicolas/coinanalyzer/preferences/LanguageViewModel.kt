package potel.nicolas.coinanalyzer.preferences

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import java.util.Locale

class LanguageViewModel() : ViewModel() {

    /**
     * Changes the app language depending on the phone's Android API version.
     *
     * @param context The application context to be modified.
     * @param languageCode The new language code such as fr-FR or en-US.
     */
    fun setLanguage(context: Context, languageCode: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)
                .applicationLocales = LocaleList.forLanguageTags(languageCode)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
        }
    }

    /**
     * Returns the current application locale's code depending on the phone's
     *  Android API version.
     *
     * @param context The application context.
     * @return The current language code, or "null" if there is no specified language.
     */
    fun getLanguageCode(context: Context): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)
                .applicationLocales[0].toLanguageTag()
                ?: Locale.getDefault().toLanguageTag()
        } else {
            AppCompatDelegate.getApplicationLocales()[0]?.toLanguageTag()
                ?: Locale.getDefault().toLanguageTag()
        }
    }
}