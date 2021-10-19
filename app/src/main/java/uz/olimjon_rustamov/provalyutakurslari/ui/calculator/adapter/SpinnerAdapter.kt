package uz.olimjon_rustamov.provalyutakurslari.ui.calculator.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import uz.olimjon_rustamov.provalyutakurslari.R
import uz.olimjon_rustamov.provalyutakurslari.databinding.ItemSpinnerBinding
import uz.olimjon_rustamov.provalyutakurslari.retrofit.model.CurrencyResponse

class SpinnerAdapter(var data: ArrayList<CurrencyResponse>) : BaseAdapter() {

    override fun getCount(): Int = data.size

    override fun getItem(position: Int): CurrencyResponse {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = ItemSpinnerBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

        Picasso.get()
            .load("https://nbu.uz/local/templates/nbu/images/flags/${data[position].code}.png")
            .error(R.drawable.uzbekistan)
            .into(view.image)

        view.code.text = data[position].code
        return view.root
    }
}