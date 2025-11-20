package potel.nicolas.coinanalyzer.recentsearch

import potel.nicolas.coinanalyzer.model.CryptoItem

class RecentSearchRepository(private val dao: RecentSearchDAO) {

    /**
     * Returns the list of recent searches in the database.
     */
    suspend fun getRecentSearches() = dao.getRecentSearches()

    /**
     * Adds a recent search in the database, and removes the last one if
     * the number of recent searches is above the constant specified in DAO.
     *
     * @param item The crypto to add as recent search.
     * @see RecentSearchDAO
     */
    suspend fun addRecentSearch(item: CryptoItem) = dao.addRecentSearch(item)

}