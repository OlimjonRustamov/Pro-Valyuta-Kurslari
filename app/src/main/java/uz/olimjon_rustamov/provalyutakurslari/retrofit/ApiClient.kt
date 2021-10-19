package uz.olimjon_rustamov.provalyutakurslari.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun getRetrofitAll(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://nbu.uz/uz/exchange-rates/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiClient= getRetrofitAll().create(ApiService::class.java)
}