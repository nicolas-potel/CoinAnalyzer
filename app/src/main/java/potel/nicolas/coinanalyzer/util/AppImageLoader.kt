package potel.nicolas.coinanalyzer.util

import coil.ImageLoader
import coil.request.CachePolicy
import okhttp3.Cache
import okhttp3.OkHttpClient
import potel.nicolas.coinanalyzer.MainApplication
import java.io.File

object AppImageLoader {

    private lateinit var imageLoader: ImageLoader

    fun init(application: MainApplication) {
        imageLoader = ImageLoader.Builder(application)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(
                        Cache(
                            directory = File(application.cacheDir, "image_cache"),
                            maxSize = 50L * 1024 * 1024 // 50 Mo
                        )
                    )
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build()
    }

    fun get(): ImageLoader {
        if (!::imageLoader.isInitialized) {
            throw IllegalStateException("AppImageLoader must be initialized in Application.onCreate()")
        }
        return imageLoader
    }
}
