package potel.nicolas.coinanalyzer.recentsearch

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import potel.nicolas.coinanalyzer.model.CryptoItem

@Dao
interface RecentSearchDAO {

    companion object {
        const val MAX_RECENT_SEARCHES = 6
    }

    /**
     * Returns the list of recent searches in the database.
     */
    @Query("SELECT * FROM recentSearches ORDER BY timestamp DESC")
    suspend fun getRecentSearches(): List<RecentSearch>

    /**
     * Adds a new recent search in the database.
     *
     * @param item The crypto to add as recent search.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecentSearchRaw(item: RecentSearch)

    /**
     * Removes a recent search in the database by its id.
     *
     * @param id The recent search's id to remove.
     */
    @Query("DELETE FROM recentSearches WHERE id = :id")
    suspend fun removeById(id: Int)

    /**
     * Adds a new recent search in the database, if the number of recent searches
     * after insertion is above MAX_RECENT_SEARCHES, removes the last one.
     * @param item The crypto to add as recent search.
     */
    @Transaction
    suspend fun addRecentSearch(item: CryptoItem) {
        addRecentSearchRaw(RecentSearch.from(item))

        val all = getRecentSearches()
        if (all.size > MAX_RECENT_SEARCHES) {
            val toRemove = all.drop(MAX_RECENT_SEARCHES)
            toRemove.forEach { removeById(it.id) }
        }
    }

}