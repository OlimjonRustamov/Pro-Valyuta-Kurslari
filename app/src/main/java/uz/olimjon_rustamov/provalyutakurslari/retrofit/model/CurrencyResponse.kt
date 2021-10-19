package uz.olimjon_rustamov.provalyutakurslari.retrofit.model

import java.io.Serializable

data class CurrencyResponse(
    var cb_price: String,
    var code: String,
    var date: String,
    var nbu_buy_price: String,
    var nbu_cell_price: String,
    var title: String
):Serializable