package potel.nicolas.coinanalyzer.model

import potel.nicolas.coinanalyzer.R
import potel.nicolas.coinanalyzer.api.CryptoData

enum class Filter(val id: String, val label: Int) {

    PRICE_DESC("price_desc", R.string.filters_price_desc) {
        override fun sort(list: List<CryptoData>, currency: Currency): List<CryptoData> =
            list.sortedByDescending { it.quote[currency.symbol]?.price ?: 0.0 }
    },

    PRICE_ASC("price_asc", R.string.filters_price_asc) {
        override fun sort(list: List<CryptoData>, currency: Currency): List<CryptoData> =
            list.sortedBy { it.quote[currency.symbol]?.price ?: 0.0 }
    },

    RANK_DESC("rank_desc", R.string.filters_rank_desc) {
        override fun sort(list: List<CryptoData>, currency: Currency): List<CryptoData> =
            list.sortedBy { it.rank }
    },

    RANK_ASC("rank_asc", R.string.filters_rank_asc) {
        override fun sort(list: List<CryptoData>, currency: Currency): List<CryptoData> =
            list.sortedByDescending { it.rank }
    };

    abstract fun sort(list: List<CryptoData>, currency: Currency): List<CryptoData>

    companion object {
        fun from(id: String): Filter =
            entries.firstOrNull { it.id == id } ?: RANK_DESC
    }
}
