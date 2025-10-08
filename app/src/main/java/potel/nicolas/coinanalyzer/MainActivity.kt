package potel.nicolas.coinanalyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import potel.nicolas.coinanalyzer.config.KoinModule
import potel.nicolas.coinanalyzer.ui.theme.ApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity) // important pour Koin Android
            modules(KoinModule)
        }

        setContent {
            ApplicationTheme {
                CoinAnalyzerApp()
            }
        }
    }
}