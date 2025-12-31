package potel.nicolas.coinanalyzer.preferences

import android.content.res.Configuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import potel.nicolas.coinanalyzer.model.Currency
import potel.nicolas.coinanalyzer.model.Filter
import potel.nicolas.coinanalyzer.model.TimeInterval


class UserPreferencesViewModel (
    private val repository: UserPreferencesRepository
) : ViewModel() {

    /**
     * List view handling.
     */
    private val _orientation = MutableStateFlow(Configuration.ORIENTATION_PORTRAIT)
    val orientation: StateFlow<Int> = _orientation

    val isListViewEnabled: StateFlow<Boolean> = _orientation
        .map { it == Configuration.ORIENTATION_LANDSCAPE }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            false
        )

    /**
     * Updates the orientation.
     *
     * @param configOrientation The new orientation.
     */
    fun updateOrientation(configOrientation: Int) {
        _orientation.value = configOrientation
    }

    /**
     * Currency handling.
     */
    val currency: StateFlow<Currency> =
        repository.currency
            .map { symbol ->
                Currency.from(symbol)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = Currency.from(UserPreferencesDefaultValues.currency)
            )

    fun setCurrency(newCurrency : Currency) {
        viewModelScope.launch {
            repository.setCurrency(newCurrency.symbol)
        }
    }

    /**
     * Time interval handling.
     */
    val timeInterval: StateFlow<TimeInterval> =
        repository.timeInterval
            .map { timeIntervalId ->
                TimeInterval.from(timeIntervalId)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = TimeInterval.from(UserPreferencesDefaultValues.timeInterval)
            )

    fun setTimeInterval(newTimeInterval : TimeInterval) {
        viewModelScope.launch {
            repository.setTimeInterval(newTimeInterval)
        }
    }

    /**
     * Filter handling.
     */
    val filter: StateFlow<Filter> =
        repository.filter
            .map { filterId ->
                Filter.from(filterId)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = Filter.from(UserPreferencesDefaultValues.filter)
            )

    fun setFilter(newFilter : Filter) {
        viewModelScope.launch {
            repository.setFilter(newFilter)
        }
    }
}