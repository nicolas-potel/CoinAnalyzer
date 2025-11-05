package potel.nicolas.coinanalyzer.api

import potel.nicolas.coinanalyzer.config.NetworkModule
import retrofit2.http.GET

interface ApiService {
    @GET("endpoint")
    suspend fun getData(): List<>
}

val apiService: ApiService = NetworkModule.retrofit.create(ApiService::class.java)