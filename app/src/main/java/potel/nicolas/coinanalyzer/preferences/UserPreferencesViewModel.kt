package potel.nicolas.coinanalyzer.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class UserPreferencesViewModel (
    private val repository: UserPreferencesRepository
) : ViewModel() {

    val isListViewEnabled: StateFlow<Boolean> =
        repository.isListViewEnabled
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = false
            )

    fun toggleListView() {
        viewModelScope.launch {
            val current = repository.isListViewEnabled.first()
            repository.setListViewEnabled(!current)
        }
    }
}