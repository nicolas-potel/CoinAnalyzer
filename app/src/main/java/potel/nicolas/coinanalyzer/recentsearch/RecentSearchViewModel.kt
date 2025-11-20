package potel.nicolas.coinanalyzer.recentsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import potel.nicolas.coinanalyzer.model.CryptoItem

class RecentSearchViewModel(
    private val repository: RecentSearchRepository
) : ViewModel() {

    private val _recentSearches = MutableStateFlow<List<RecentSearch>>(emptyList())
    val recentSearches: StateFlow<List<RecentSearch>> = _recentSearches

    init {
        loadRecentSearches()
    }

    /**
     * Loads the recent searches in database.
     */
    fun loadRecentSearches() {
        viewModelScope.launch {
            _recentSearches.value = repository.getRecentSearches()
        }
    }

    /**
     * Add a recent search in the database.
     *
     * @param item The crypto to add in recent searches.
     */
    fun addRecentSearch(item: CryptoItem) {
        viewModelScope.launch {
            repository.addRecentSearch(item)
            loadRecentSearches()
        }
    }
}
