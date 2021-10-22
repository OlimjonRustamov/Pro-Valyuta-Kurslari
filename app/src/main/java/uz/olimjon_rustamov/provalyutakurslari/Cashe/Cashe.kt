package uz.olimjon_rustamov.provalyutakurslari.Cashe

import android.content.Context
import android.content.SharedPreferences
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.model.CurrencyResponse

class Cashe private constructor(context: Context) {

    init {
        preferences = context.getSharedPreferences("details", Context.MODE_PRIVATE)
    }

    fun setLastDateUpdated(date:String) {
        editor = preferences.edit()
        editor!!.putString("date",date)
        editor!!.apply()
    }

    fun getLastDateUpdated(): String {
        return preferences.getString("date", "")!!
    }

    fun clear() {
        preferences.edit().clear().apply()
    }

    companion object {
        var instance: Cashe? = null
            private set
        private lateinit var preferences: SharedPreferences
        private var editor: SharedPreferences.Editor?=null

        fun init(context: Context) {
            if (instance == null) {
                instance = Cashe(context)
            }
        }
    }

}