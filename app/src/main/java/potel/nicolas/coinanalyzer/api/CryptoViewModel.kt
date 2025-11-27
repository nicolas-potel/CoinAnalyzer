package potel.nicolas.coinanalyzer.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import potel.nicolas.coinanalyzer.BuildConfig
import potel.nicolas.coinanalyzer.config.NetworkModule.cryptoApi
import potel.nicolas.coinanalyzer.model.CryptoItem
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel

class CryptoViewModel(
    userPreferencesViewModel: UserPreferencesViewModel
) : ViewModel() {

    private val _cryptos = MutableStateFlow<List<CryptoData>>(emptyList())
    val cryptos: StateFlow<List<CryptoData>> = _cryptos

    private val _cryptoDetails = MutableStateFlow<CryptoDetails?>(null)
    val selectedCryptoDetails: StateFlow<CryptoDetails?> = _cryptoDetails

    init {
        viewModelScope.launch {
            userPreferencesViewModel.currency.collect { currency ->
                fetchCryptos(currency.symbol)
            }
        }
    }

    /**
     * Fetches the data for all available cryptos.
     *
     * @param currency The user's selected currency.
     */
    private fun fetchCryptos(currency : String) {
        viewModelScope.launch {
            val response = cryptoApi.getCryptos(BuildConfig.API_KEY, currency)

            val sortedCryptos = response.data.sortedBy { it.rank }
            _cryptos.value = sortedCryptos
        }
    }

    /**
     * Fetches the crypto details from API.
     *
     * @param crypto The crypto to search details for.
     */
    fun getCryptoDetails(crypto : CryptoItem) {
        viewModelScope.launch {
            try {
                val response = cryptoApi.getCryptoDetails(BuildConfig.API_KEY, crypto.symbol)
                _cryptoDetails.value = response.data[crypto.symbol]
            } catch (_: Exception) {
                _cryptoDetails.value = null
            }
        }
    }
}
