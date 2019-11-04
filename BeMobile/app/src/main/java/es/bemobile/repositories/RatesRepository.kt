package es.bemobile.repositories

import es.bemobile.service.RetrofitClient
import es.bemobile.service.Webservice

class RatesRepository {
    var client: Webservice = RetrofitClient.webservice
    suspend fun getRates() = client.getRates()
}