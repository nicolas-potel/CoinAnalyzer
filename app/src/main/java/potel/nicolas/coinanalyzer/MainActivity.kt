package potel.nicolas.coinanalyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import potel.nicolas.coinanalyzer.components.AppLanguageWrapper
import potel.nicolas.coinanalyzer.ui.theme.ApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ApplicationTheme {
                AppLanguageWrapper(
                    content = { CoinAnalyzerApp() },
                    languageViewModel = viewModel()
                )
            }
        }
    }

}