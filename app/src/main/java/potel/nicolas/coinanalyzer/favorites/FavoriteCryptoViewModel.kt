package potel.nicolas.coinanalyzer.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteCryptoViewModel(
    private val repository: FavoriteCryptoRepository
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<FavoriteCrypto>>(emptyList())
    val favorites: StateFlow<List<FavoriteCrypto>> = _favorites

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = repository.getAllFavorites()
        }
    }

    fun addFavorite(item: FavoriteCrypto) {
        viewModelScope.launch {
            repository.addFavorite(item)
            loadFavorites()
        }
    }

    fun removeFavorite(item: FavoriteCrypto) {
        viewModelScope.launch {
            repository.removeFavorite(item)
            loadFavorites()
        }
    }

    suspend fun isFavorite(itemId: Int): Boolean {
        return repository.isFavorite(itemId)
    }
}
