package uz.olimjon_rustamov.provalyutakurslari.ui.home.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.olimjon_rustamov.provalyutakurslari.databinding.ItemCurrency2Binding
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.model.CurrencyResponse

class CurrencyRvAdapter() :
    RecyclerView.Adapter<CurrencyRvAdapter.VH>() {

    private lateinit var data: List<CurrencyResponse>

    fun setAdapter(data: List<CurrencyResponse>) {
        this.data = data
    }

    inner class VH(var itemCurrency2Binding: ItemCurrency2Binding) :
        RecyclerView.ViewHolder(itemCurrency2Binding.root) {

        fun onBind(currency: CurrencyResponse) {
            itemCurrency2Binding.currency = currency
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCurrency2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int = data.size
}