package potel.nicolas.coinanalyzer.config

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import potel.nicolas.coinanalyzer.favorites.FavoriteCrypto
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoDAO
import potel.nicolas.coinanalyzer.recentsearch.RecentSearchDAO

@Database(entities = [FavoriteCrypto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteCryptoDAO
    abstract fun recentSearchDao(): RecentSearchDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
