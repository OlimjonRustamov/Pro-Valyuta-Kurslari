package uz.olimjon_rustamov.provalyutakurslari.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import uz.olimjon_rustamov.provalyutakurslari.retrofit.model.CurrencyResponse

interface ApiService {
    @GET("json")
    fun getCurrencies(): Call<List<CurrencyResponse>>
}