package potel.nicolas.coinanalyzer.api

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import potel.nicolas.coinanalyzer.BuildConfig
import potel.nicolas.coinanalyzer.config.NetworkModule.cryptoApi
import potel.nicolas.coinanalyzer.favorites.FavoriteCryptoRepository
import potel.nicolas.coinanalyzer.model.CryptoItem
import potel.nicolas.coinanalyzer.offline.CryptoEntityRepository
import potel.nicolas.coinanalyzer.offline.QuoteMapConverter
import potel.nicolas.coinanalyzer.offline.toCryptoData
import potel.nicolas.coinanalyzer.preferences.UserPreferencesViewModel

class CryptoViewModel(
    private val cryptoEntityRepository: CryptoEntityRepository,
    userPreferencesViewModel: UserPreferencesViewModel
) : ViewModel() {

    private val _cryptos = MutableStateFlow<List<CryptoData>>(emptyList())
    val cryptos: StateFlow<List<CryptoData>> = _cryptos

    private val _cryptoDetails = MutableStateFlow<CryptoDetailsData?>(null)
    val selectedCryptoDetails: StateFlow<CryptoDetailsData?> = _cryptoDetails

    private val _selectedCrypto = MutableStateFlow<CryptoData?>(null)
    val selectedCrypto = _selectedCrypto

    init {
        viewModelScope.launch {
            userPreferencesViewModel.currency.collect { currency ->
                loadCryptos(currency.symbol)
            }
        }
    }

    /**
     * Loads all crypto data from the API and stores it in the database.
     * If the data could not be retrieved from api, uses the data from the database.
     *
     * @param currencySymbol The user's selected currency.
     */
    private suspend fun loadCryptos(currencySymbol: String) {
        val converter = QuoteMapConverter()

        try {
            val fetched = fetchCryptos(currencySymbol)

            if (fetched.isEmpty()) {
                _cryptos.value = cryptoEntityRepository.getAll().map { it.toCryptoData(converter) }
            } else {
                _cryptos.value = fetched
                cryptoEntityRepository.insertAll(fetched.map { it.toEntity(converter) })
            }

        } catch (e: Exception) {
            _cryptos.value = cryptoEntityRepository.getAll().map { it.toCryptoData(converter) }
            Log.e("CryptoViewModel", "Error fetching cryptos", e)
        }
    }


    /**
     * Returns the corresponding crypto data using the specified crypto item's id.
     * @param cryptoItem The specified crypto item.
     */
    fun getDataFromCryptoItem(cryptoItem: CryptoItem) : CryptoData {
        val crypto = _cryptos.value.find { it.id == cryptoItem.id }
        if (crypto != null) {
            return crypto
        }
        throw Exception("Specified crypto item not found : ${cryptoItem.id}")
    }

    /**
     * Sets the selected crypto to specified one.
     *
     * @param crypto The new selected crypto.
     */
    fun setSelectedCrypto(crypto : CryptoData) {
        _selectedCrypto.value = crypto
    }

    /**
     * Fetches the data for all available cryptos and returns it.
     *
     * @param currency The user's selected currency.
     */
    private suspend fun fetchCryptos(currency : String): List<CryptoData> {
        val response = cryptoApi.getCryptos(
            BuildConfig.API_KEY,
            currency
        )

        return response.data.sortedBy { it.rank }
    }

    /**
     * Fetches the crypto details from API.
     *
     * @param crypto The crypto to search details for.
     */
    fun getCryptoDetails(crypto : CryptoItem) {
        viewModelScope.launch {
            try {
                val response = cryptoApi.getCryptoDetails(
                    BuildConfig.API_KEY,
                    crypto.symbol)
                _cryptoDetails.value = response.data[crypto.symbol]
            } catch (_: Exception) {
                _cryptoDetails.value = null
            }
        }
    }
}
