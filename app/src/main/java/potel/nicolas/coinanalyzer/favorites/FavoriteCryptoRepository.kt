package potel.nicolas.coinanalyzer.favorites

class FavoriteCryptoRepository(private val dao: FavoriteCryptoDAO) {

    suspend fun getAllFavorites() = dao.getAllFavorites()
    suspend fun addFavorite(item: FavoriteCrypto) = dao.addFavorite(item)
    suspend fun removeFavorite(item: FavoriteCrypto) = dao.removeFavorite(item)
    fun isFavoriteFlow(itemId: Int) = dao.isFavoriteFlow(itemId)
}