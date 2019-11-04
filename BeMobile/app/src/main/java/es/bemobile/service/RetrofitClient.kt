package es.bemobile.service

import com.google.gson.GsonBuilder
import es.bemobile.config.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(Webservice::class.java)
    }
}