package potel.nicolas.coinanalyzer.config

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.TypeConverters
import potel.nicolas.coinanalyzer.favorites.FavoriteCrypto
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoDAO
import potel.nicolas.coinanalyzer.offline.CryptoEntity
import potel.nicolas.coinanalyzer.offline.CryptoEntityDAO
import potel.nicolas.coinanalyzer.offline.QuoteMapConverter
import potel.nicolas.coinanalyzer.recentsearch.RecentSearch
import potel.nicolas.coinanalyzer.recentsearch.RecentSearchDAO

/**
 * Room database for CoinAnalyzer application.
 */
@Database(
    entities = [
        FavoriteCrypto::class,
        RecentSearch::class,
        CryptoEntity::class
               ],
    version = 1
)
@TypeConverters(QuoteMapConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteCryptoDAO
    abstract fun recentSearchDao(): RecentSearchDAO
    abstract fun cryptoEntityDao(): CryptoEntityDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
