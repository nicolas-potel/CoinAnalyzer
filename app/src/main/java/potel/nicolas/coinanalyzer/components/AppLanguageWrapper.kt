package potel.nicolas.coinanalyzer.components

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import potel.nicolas.coinanalyzer.preferences.LanguageViewModel

@Composable
fun AppLanguageWrapper(
    content : @Composable () -> Unit,
    languageViewModel : LanguageViewModel
) {
    val context = LocalContext.current
    val activity = context as? Activity

    val shouldRestart by languageViewModel.shouldRestartActivity.collectAsState()

    LaunchedEffect(shouldRestart) {
        if (shouldRestart && activity != null) {
            val intent = activity.intent
            activity.finish()
            activity.startActivity(intent)
            languageViewModel.consumeRestartEvent()
        }
    }

    content()
}