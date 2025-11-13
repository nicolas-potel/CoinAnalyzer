package potel.nicolas.coinanalyzer.favorites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCryptoDAO {

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<FavoriteCrypto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(item: FavoriteCrypto)

    @Delete
    suspend fun removeFavorite(item: FavoriteCrypto)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :cryptoId)")
    fun isFavoriteFlow(cryptoId: Int): Flow<Boolean>
}