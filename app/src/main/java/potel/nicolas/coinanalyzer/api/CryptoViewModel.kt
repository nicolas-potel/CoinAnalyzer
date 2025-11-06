package potel.nicolas.coinanalyzer.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import potel.nicolas.coinanalyzer.BuildConfig
import potel.nicolas.coinanalyzer.config.NetworkModule.cryptoApi
import potel.nicolas.coinanalyzer.model.CryptoData

class CryptoViewModel : ViewModel() {

    private val _cryptos = MutableStateFlow<List<CryptoData>>(emptyList())
    val cryptos: StateFlow<List<CryptoData>> = _cryptos

    init {
        fetchCryptos()
    }

    private fun fetchCryptos() {
        viewModelScope.launch {
            val response = cryptoApi.getCryptos(BuildConfig.API_KEY, "USD")

            val sortedCryptos = response.data.sortedBy { it.rank }
            _cryptos.value = sortedCryptos
        }
    }
}
