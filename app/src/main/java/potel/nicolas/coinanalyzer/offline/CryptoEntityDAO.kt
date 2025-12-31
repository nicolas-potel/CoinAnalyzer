package potel.nicolas.coinanalyzer.offline

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * DAO for crypto entities.
 */
@Dao
interface CryptoEntityDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cryptos: List<CryptoEntity>)

    @Query("SELECT * FROM crypto_entity ORDER BY rank ASC")
    suspend fun getAll(): List<CryptoEntity>

    @Query("DELETE FROM crypto_entity")
    suspend fun clear()
}