package potel.nicolas.coinanalyzer.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteCryptoViewModel(
    private val repository: FavoriteCryptoRepository
) : ViewModel() {

    val favorites: StateFlow<List<FavoriteCrypto>> = repository.getAllFavoritesFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    fun addFavorite(item: FavoriteCrypto) {
        viewModelScope.launch {
            repository.addFavorite(item)
        }
    }

    fun removeFavorite(item: FavoriteCrypto) {
        viewModelScope.launch {
            repository.removeFavorite(item)
        }
    }

    fun isFavorite(itemId: Int): Flow<Boolean> {
        return repository.isFavoriteFlow(itemId)
    }
}
