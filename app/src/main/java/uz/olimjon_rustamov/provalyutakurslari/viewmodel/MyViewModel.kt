package uz.olimjon_rustamov.provalyutakurslari.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.olimjon_rustamov.provalyutakurslari.retrofit.ApiClient
import uz.olimjon_rustamov.provalyutakurslari.retrofit.model.CurrencyResponse

class MyViewModel : ViewModel() {
    var liveData = MutableLiveData<List<CurrencyResponse>>()

    fun getCurrencies(): LiveData<List<CurrencyResponse>> {
        ApiClient.apiClient.getCurrencies().enqueue(object:Callback<List<CurrencyResponse>>{
            override fun onResponse(
                call: Call<List<CurrencyResponse>>?,
                response: Response<List<CurrencyResponse>>
            ) {
                if (response.isSuccessful) {
                    liveData.value=response.body()
                }
            }

            override fun onFailure(call: Call<List<CurrencyResponse>>?, t: Throwable?) {
                Log.d("TTTT", "onFailure: ${t?.message}")
            }

        })
        return liveData
    }
}