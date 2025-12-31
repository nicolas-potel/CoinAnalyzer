package potel.nicolas.coinanalyzer

import android.app.Application

import potel.nicolas.coinanalyzer.config.AppDatabase
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoRepository
import potel.nicolas.coinanalyzer.preferences.UserPreferencesRepository
import potel.nicolas.coinanalyzer.recentsearch.RecentSearchRepository
import potel.nicolas.coinanalyzer.util.ViewModels

class MainApplication : Application() {

    // Room database
    lateinit var appDatabase: AppDatabase private set

    // Repositories
    lateinit var userPreferencesRepository: UserPreferencesRepository private set
    lateinit var favoriteCryptoRepository: FavoriteCryptoRepository private set
    lateinit var recentSearchRepository: RecentSearchRepository private set

    override fun onCreate() {
        super.onCreate()
        appDatabase = AppDatabase.getDatabase(this)

        userPreferencesRepository = UserPreferencesRepository(this)
        favoriteCryptoRepository = FavoriteCryptoRepository(appDatabase.favoriteDao())
        recentSearchRepository = RecentSearchRepository(appDatabase.recentSearchDao())

        ViewModels.init(this)
    }

}