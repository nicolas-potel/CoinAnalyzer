package potel.nicolas.coinanalyzer.favorites

class FavoriteCryptoRepository(private val dao: FavoriteCryptoDAO) {

    /**
     * Returns the list of all favorites crypto in database.
     */
    suspend fun getAllFavorites() = dao.getAllFavorites()

    /**
     * Adds a new favorite crypto in database.
     */
    suspend fun addFavorite(item: FavoriteCrypto) = dao.addFavorite(item)

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