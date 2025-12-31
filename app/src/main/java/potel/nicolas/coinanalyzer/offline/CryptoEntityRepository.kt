package potel.nicolas.coinanalyzer.offline

class CryptoEntityRepository(private val dao: CryptoEntityDAO) {

    /**
     * Returns the list of all crypto entities in database.
     */
    suspend fun getAll() = dao.getAll()

    /**
     * Replaces all the crypto entities in the database.
     */
    suspend fun insertAll(items: List<CryptoEntity>) = dao.insertAll(items)

}