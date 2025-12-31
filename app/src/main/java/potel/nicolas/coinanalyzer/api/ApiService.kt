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

    @GET("v1/cryptocurrency/info")
    suspend fun getCryptoDetails(
        @Header("X-CMC_PRO_API_KEY") apiKey: String,
        @Query("symbol") symbol: String,
    ): CryptoDetailsResponse

}