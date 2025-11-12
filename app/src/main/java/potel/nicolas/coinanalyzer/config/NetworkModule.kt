package potel.nicolas.coinanalyzer.config

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import potel.nicolas.coinanalyzer.api.CryptoApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkModule {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pro-api.coinmarketcap.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val cryptoApi: CryptoApi = retrofit.create(CryptoApi::class.java)
}