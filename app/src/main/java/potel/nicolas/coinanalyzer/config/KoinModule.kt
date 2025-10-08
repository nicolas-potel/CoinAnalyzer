package potel.nicolas.coinanalyzer.config

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import potel.nicolas.coinanalyzer.preferences.UserPreferencesRepository
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel

val KoinModule = module {

    single { UserPreferencesRepository(androidContext()) }

    viewModel { UserPreferencesViewModel(get()) }
}