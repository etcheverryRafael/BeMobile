package es.bemobile.repositories

import es.bemobile.service.RetrofitClient
import es.bemobile.service.Webservice

class TransactionsRepository {
    var client: Webservice = RetrofitClient.webservice
    suspend fun getTransactions() = client.getTransactions()
}