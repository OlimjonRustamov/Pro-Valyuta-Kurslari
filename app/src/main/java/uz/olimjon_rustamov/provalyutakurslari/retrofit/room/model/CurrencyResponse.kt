package uz.olimjon_rustamov.provalyutakurslari.retrofit.room.model

import android.support.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "currency")
class CurrencyResponse {
    @ColumnInfo
    var cb_price: String? = null
//
//    @PrimaryKey(autoGenerate = true)
//    var id: Int? = null

    @PrimaryKey
    @NonNull
    var code: String=""

    @ColumnInfo
    var date: String? = null
    @ColumnInfo
    var nbu_buy_price: String? = null
    @ColumnInfo
    var nbu_cell_price: String? = null
    @ColumnInfo
    var title: String? = null

    constructor()

    @Ignore
    constructor(cb_price: String?, code: String, date: String?, nbu_buy_price: String?, nbu_cell_price: String?, title: String?) {
        this.cb_price = cb_price
        this.code = code
        this.date = date
        this.nbu_buy_price = nbu_buy_price
        this.nbu_cell_price = nbu_cell_price
        this.title = title
    }

//    constructor(cb_price: String?, id: Int?, code: String?, date: String?, nbu_buy_price: String?, nbu_cell_price: String?, title: String?) {
//        this.cb_price = cb_price
//        this.id = id
//        this.code = code
//        this.date = date
//        this.nbu_buy_price = nbu_buy_price
//        this.nbu_cell_price = nbu_cell_price
//        this.title = title
//    }

}