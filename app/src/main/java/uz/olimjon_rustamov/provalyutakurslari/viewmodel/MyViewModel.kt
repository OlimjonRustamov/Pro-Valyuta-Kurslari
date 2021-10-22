package uz.olimjon_rustamov.provalyutakurslari.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.olimjon_rustamov.provalyutakurslari.Cashe.Cashe
import uz.olimjon_rustamov.provalyutakurslari.retrofit.ApiClient
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.database.AppDatabase
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.model.CurrencyResponse

class MyViewModel : ViewModel() {
    var liveData = MutableLiveData<List<CurrencyResponse>>()

    fun getCurrencies(): LiveData<List<CurrencyResponse>> {
        ApiClient.apiClient.getCurrencies().enqueue(object:Callback<List<CurrencyResponse>>{
            override fun onResponse(
                call: Call<List<CurrencyResponse>>?,
                response: Response<List<CurrencyResponse>>
            ) {
                if (response.isSuccessful) {
                    liveData.value = response.body()
                    //Last Date is saved in Cashe
                    val casheLastDate = Cashe.instance!!.getLastDateUpdated()
                    val date=response.body()[0].date
                    if (date != casheLastDate) {
                        Cashe.instance?.setLastDateUpdated(response.body()[0].date.toString())
                        response.body().forEach {
                            AppDatabase.get.getDatabase().getDao().insertCurrency(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<CurrencyResponse>>?, t: Throwable?) {
                val lastDate = Cashe.instance!!.getLastDateUpdated()
                val dao = AppDatabase.get.getDatabase().getDao()
                liveData.value = dao.getCurrenciesLast(lastDate)
            }

        })
        return liveData
    }
}