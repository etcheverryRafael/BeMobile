package es.bemobile.service

import es.bemobile.config.Constants
import es.bemobile.models.Rate
import es.bemobile.models.Transaction
import retrofit2.http.GET

interface Webservice {
    @GET(Constants.TRANSACTIONS_JSON_ENDPOINT_URL)
    suspend fun getTransactions(): List<Transaction>

    @GET(Constants.RATES_JSON_ENDPOINT_URL)
    suspend fun getRates(): List<Rate>
}