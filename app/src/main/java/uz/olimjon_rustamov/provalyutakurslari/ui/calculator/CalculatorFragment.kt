package uz.olimjon_rustamov.provalyutakurslari.ui.calculator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.olimjon_rustamov.provalyutakurslari.databinding.FragmentCalculatorBinding
import uz.olimjon_rustamov.provalyutakurslari.retrofit.ApiClient
import uz.olimjon_rustamov.provalyutakurslari.retrofit.model.CurrencyResponse
import uz.olimjon_rustamov.provalyutakurslari.ui.calculator.adapter.SpinnerAdapter
import uz.olimjon_rustamov.provalyutakurslari.viewmodel.MyViewModel

class CalculatorFragment : Fragment() {

    private lateinit var myViewModel: MyViewModel
    private lateinit var currencies: ArrayList<CurrencyResponse>
    private var position = -1
    private lateinit var binding: FragmentCalculatorBinding
    lateinit var spinnerAdapter1: SpinnerAdapter
    lateinit var spinnerAdapter2: SpinnerAdapter

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
        loadCurrenciesAdapter()



        return binding.root
    }

    private fun loadCurrenciesAdapter() {
        myViewModel.getCurrencies().observe(viewLifecycleOwner, Observer {
            currencies = it as ArrayList<CurrencyResponse>
            currencies.add(CurrencyResponse("1", "UZS", "null", "1", "1", ""))

            spinnerAdapter1 = SpinnerAdapter(currencies)
            spinnerAdapter2 = SpinnerAdapter(currencies)
            binding.spinner1.adapter = spinnerAdapter1
            binding.spinner2.adapter = spinnerAdapter2

            if (position != -1) {
                binding.spinner1.setSelection(position)
            }
            binding.spinner2.setSelection(currencies.size - 1)

            setVisiblities()
        })
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