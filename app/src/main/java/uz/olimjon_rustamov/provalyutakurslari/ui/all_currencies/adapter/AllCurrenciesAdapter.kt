package uz.olimjon_rustamov.provalyutakurslari.ui.all_currencies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.olimjon_rustamov.provalyutakurslari.R
import uz.olimjon_rustamov.provalyutakurslari.databinding.ItemCurrencyBinding
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.model.CurrencyResponse

class AllCurrenciesAdapter(var currencies: List<CurrencyResponse>, var onItemClick: OnItemClick) :
    RecyclerView.Adapter<AllCurrenciesAdapter.Vh>() {
    inner class Vh(var itemCurrencyBinding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(itemCurrencyBinding.root) {

        fun onBind(currency: CurrencyResponse, position: Int) {
            itemCurrencyBinding.currency = currency

            Picasso.get()
                .load("https://nbu.uz/local/templates/nbu/images/flags/${currency.code}.png")
                .into(itemCurrencyBinding.image)

            itemView.setOnClickListener {
                onItemClick.onItemClickListener(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        val inflater = LayoutInflater.from(parent.context)
        return Vh(DataBindingUtil.inflate(inflater, R.layout.item_currency, parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(currencies[position], position)
    }

    override fun getItemCount(): Int = currencies.size

    interface OnItemClick {
        fun onItemClickListener(position: Int)
    }
}