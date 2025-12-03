package potel.nicolas.coinanalyzer.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.time.Instant

interface CryptoApi {

    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getCryptos(
        @Header("X-CMC_PRO_API_KEY") apiKey: String,
        @Query("convert") fiat: String
    ): CryptoDataResponse

    @GET("v1/cryptocurrency/quotes/historical")
    suspend fun getHistoricalQuotes(
        @Header("X-CMC_PRO_API_KEY") apiKey: String,
        @Query("symbol") symbol: String,
        @Query("interval") interval: String,
        @Query("convert") convert: String,
        @Query("time_start") timeStart: Instant,
    ): CryptoDetailsResponse

}