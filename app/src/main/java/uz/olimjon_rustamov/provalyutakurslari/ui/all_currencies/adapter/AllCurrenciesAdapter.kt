package uz.olimjon_rustamov.provalyutakurslari.ui.all_currencies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.olimjon_rustamov.provalyutakurslari.R
import uz.olimjon_rustamov.provalyutakurslari.databinding.ItemCurrencyBinding
import uz.olimjon_rustamov.provalyutakurslari.retrofit.model.CurrencyResponse

class AllCurrenciesAdapter(var currencies:List<CurrencyResponse>):RecyclerView.Adapter<AllCurrenciesAdapter.Vh>(){
    inner class Vh(var itemCurrencyBinding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(itemCurrencyBinding.root) {

        fun onBind(currency: CurrencyResponse) {
            itemCurrencyBinding.currency=currency
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        val inflater=LayoutInflater.from(parent.context)
        return Vh(DataBindingUtil.inflate(inflater, R.layout.item_currency, parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(currencies[position])
    }

    override fun getItemCount(): Int = currencies.size
}