package potel.nicolas.coinanalyzer.favorites

import kotlinx.coroutines.flow.Flow

class FavoriteCryptoRepository(private val dao: FavoriteCryptoDAO) {

    /**
     * Returns the list of all favorites crypto in database.
     */
    suspend fun getAllFavorites() = dao.getAllFavorites()

    /**
     * Returns all favorite cryptos from the database.
     */
    fun getAllFavoritesFlow(): Flow<List<FavoriteCrypto>> = dao.getAllFavoritesFlow()

    /**
     * Adds a new favorite crypto in database.
     */
    suspend fun addFavorite(item: FavoriteCrypto) = dao.addFavorite(item)

    /**
     * Adds a list of new favorite crypto in database.
     */
    suspend fun insertAll(items: List<FavoriteCrypto>) = dao.insertAll(items)

    /**
     * Removes a favorite crypto from the database.
     */
    suspend fun removeFavorite(item: FavoriteCrypto) = dao.removeFavorite(item)

    /**
     * Returns true if the favorite crypto specified by its id is in the database,
     * false otherwise.
     */
    fun isFavoriteFlow(itemId: Int) = dao.isFavoriteFlow(itemId)

}