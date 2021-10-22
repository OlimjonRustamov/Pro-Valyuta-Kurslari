package uz.olimjon_rustamov.provalyutakurslari.retrofit.room.dao

import androidx.room.*
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.model.CurrencyResponse

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrency(currency:CurrencyResponse)

    @Update
    fun updateCurrency(currency:CurrencyResponse)

    @Query("select * from currency where currency.date=:date")
    fun getCurrenciesLast(date:String):List<CurrencyResponse>

    @Query("select * from currency where currency.code=:code")
    fun getAllByCode(code: String): List<CurrencyResponse>
}