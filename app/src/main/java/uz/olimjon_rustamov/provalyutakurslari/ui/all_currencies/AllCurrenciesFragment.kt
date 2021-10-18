package uz.olimjon_rustamov.provalyutakurslari.ui.all_currencies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import uz.olimjon_rustamov.provalyutakurslari.R
import uz.olimjon_rustamov.provalyutakurslari.databinding.FragmentAllCurrenciesBinding
import uz.olimjon_rustamov.provalyutakurslari.retrofit.model.CurrencyResponse
import uz.olimjon_rustamov.provalyutakurslari.ui.all_currencies.adapter.AllCurrenciesAdapter
import uz.olimjon_rustamov.provalyutakurslari.viewmodel.MyViewModel

class AllCurrenciesFragment : Fragment() {

    private lateinit var binding: FragmentAllCurrenciesBinding

    private lateinit var myViewModel: MyViewModel
    private lateinit var currencies: List<CurrencyResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCurrenciesBinding.inflate(layoutInflater)
        initViewModel()
        loadCurrencies()

        return binding.root
    }

    private fun loadAdapter() {
        val rv = binding.allCurrenciesRv
        val adapter = AllCurrenciesAdapter(currencies)
        rv.layoutManager = LinearLayoutManager(binding.root.context)
        rv.adapter = adapter
    }

    private fun loadCurrencies() {
        myViewModel.getCurrencies().observe(viewLifecycleOwner, Observer {
            currencies = it
            loadAdapter()
        })

    }

    private fun initViewModel() {
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
    }
}