package potel.nicolas.coinanalyzer.preferences

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import potel.nicolas.coinanalyzer.model.Currency
import java.util.Locale


class UserPreferencesViewModel (
    private val repository: UserPreferencesRepository
) : ViewModel() {

    /**
     * List view handling.
     */
    val isListViewEnabled: StateFlow<Boolean> =
        repository.isListViewEnabled
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UserPreferencesDefaultValues.listViewEnabled
            )

    fun toggleListView() {
        viewModelScope.launch {
            val current = repository.isListViewEnabled.first()
            repository.setListViewEnabled(!current)
        }
    }

    /**
     * Currency handling.
     */
    val currency: StateFlow<String> =
        repository.currency
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UserPreferencesDefaultValues.currency
            )

    fun setCurrency(newCurrency : Currency) {
        viewModelScope.launch {
            repository.setCurrency(newCurrency.displayName)
        }
    }

    /**
     * Language handling.
     */
    val currentLanguage: StateFlow<String> =
        repository.language
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UserPreferencesDefaultValues.language
            )

    fun setLanguage(languageCode: String) {
        viewModelScope.launch {
            repository.setLanguage(languageCode)
        }
    }
}