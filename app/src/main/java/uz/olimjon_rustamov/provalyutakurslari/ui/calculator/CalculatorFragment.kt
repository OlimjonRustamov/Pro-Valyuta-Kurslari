package uz.olimjon_rustamov.provalyutakurslari.ui.calculator

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import uz.olimjon_rustamov.provalyutakurslari.databinding.FragmentCalculatorBinding
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.model.CurrencyResponse
import uz.olimjon_rustamov.provalyutakurslari.ui.calculator.adapter.SpinnerAdapter
import uz.olimjon_rustamov.provalyutakurslari.viewmodel.MyViewModel
import java.lang.Exception

class CalculatorFragment : Fragment() {

    private lateinit var myViewModel: MyViewModel
    private lateinit var currencies: ArrayList<CurrencyResponse>
    private var position = -1
    private lateinit var binding: FragmentCalculatorBinding
    lateinit var spinnerAdapter1: SpinnerAdapter
    lateinit var spinnerAdapter2: SpinnerAdapter
    lateinit var fromConvert: CurrencyResponse
    lateinit var toConvert: CurrencyResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt("position")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCalculatorBinding.inflate(layoutInflater, container, false)
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        fromConvert = CurrencyResponse()
        toConvert= CurrencyResponse()

        loadCurrenciesAdapter()



        return binding.root
    }

    private fun loadCurrenciesAdapter() {
        myViewModel.getCurrencies().observe(viewLifecycleOwner, Observer {
            currencies = it as ArrayList<CurrencyResponse>
            setSpinnersAndEdit()
            setVisiblities()
        })
    }

    private fun setSpinnersAndEdit() {
        currencies.add(CurrencyResponse("1", "UZS", "null", "1", "1", ""))

        spinnerAdapter1 = SpinnerAdapter(currencies)
        spinnerAdapter2 = SpinnerAdapter(currencies)
        binding.spinner1.adapter = spinnerAdapter1
        binding.spinner2.adapter = spinnerAdapter2

        if (position != -1) {
            binding.spinner1.setSelection(position)
        }
        binding.spinner2.setSelection(currencies.size - 1)
        fromConvert = currencies[binding.spinner1.selectedItemPosition]
        toConvert = currencies[binding.spinner2.selectedItemPosition]

        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                fromConvert = currencies[position]
                if (fromConvert.nbu_buy_price == "") {
                    fromConvert.nbu_buy_price = fromConvert.cb_price
                    fromConvert.nbu_cell_price = fromConvert.cb_price
                }

                editTextChanged(binding.edit.text)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                toConvert = currencies[position]
                //response might be empty
                if (toConvert.nbu_buy_price == "") {
                    toConvert.nbu_buy_price = toConvert.cb_price
                    toConvert.nbu_cell_price = toConvert.cb_price
                }
                editTextChanged(binding.edit.text)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        binding.edit.addTextChangedListener {
            editTextChanged(it)
        }
    }

    private fun editTextChanged(it: Editable?) {
        var amount=0.0
        try {
            amount = it.toString().toDouble()
        } catch (e: Exception) {
            amount=0.0
        }
        val convertedBuy =
            amount * (fromConvert.nbu_buy_price!!.toDouble()) / (toConvert.nbu_buy_price!!.toDouble())
        val convertedSell =
            amount * (fromConvert.nbu_cell_price!!.toDouble()) / (toConvert.nbu_cell_price!!.toDouble())
        binding.buy.text = convertedBuy.toString()
        binding.sell.text = convertedSell.toString()
    }

    private fun setVisiblities() {
        binding.apply {
            progressCalculator.visibility = View.GONE
            spinner1.visibility = View.VISIBLE
            spinner2.visibility = View.VISIBLE
            exchange.visibility = View.VISIBLE
            edit.visibility = View.VISIBLE
            sell.visibility = View.VISIBLE
            buy.visibility = View.VISIBLE
            buyTitle.visibility = View.VISIBLE
            sellTitle.visibility = View.VISIBLE
            dollar.visibility = View.VISIBLE
        }
    }
}