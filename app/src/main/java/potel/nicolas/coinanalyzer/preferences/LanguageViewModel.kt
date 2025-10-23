package potel.nicolas.coinanalyzer.preferences

import android.app.LocaleManager
import android.content.Context
import android.os.LocaleList
import androidx.lifecycle.ViewModel
import java.util.Locale

class LanguageViewModel() : ViewModel() {

    private var currentLang: String? = null

    /**
     * Changes the app language depending on the phone's Android API version.
     *
     * @param context The application context to be modified.
     * @param languageCode The new language code such as fr-FR or en-US.
     */
    fun setLanguage(context: Context, languageCode: String) {
        if (languageCode == currentLang) return
        currentLang = languageCode

        context.getSystemService(LocaleManager::class.java)
            .applicationLocales = LocaleList.forLanguageTags(languageCode)
    }

    /**
     * Returns the current application locale's code depending on the phone's
     *  Android API version.
     *
     * @param context The application context.
     * @return The current language code.
     */
    fun getLanguageCode(context: Context): String {
        return context.getSystemService(LocaleManager::class.java)
                .applicationLocales[0]?.toLanguageTag()
                ?: Locale.getDefault().toLanguageTag().split("-").first()

    }
}