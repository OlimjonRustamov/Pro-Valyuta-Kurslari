package uz.olimjon_rustamov.provalyutakurslari.ui.all_currencies

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.olimjon_rustamov.provalyutakurslari.R
import uz.olimjon_rustamov.provalyutakurslari.databinding.FragmentAllCurrenciesBinding
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.dao.CurrencyDao
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.database.AppDatabase
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.model.CurrencyResponse
import uz.olimjon_rustamov.provalyutakurslari.ui.all_currencies.adapter.AllCurrenciesAdapter
import uz.olimjon_rustamov.provalyutakurslari.viewmodel.MyViewModel

class AllCurrenciesFragment : Fragment() {

    private lateinit var binding: FragmentAllCurrenciesBinding
    private lateinit var dao:CurrencyDao
    private lateinit var myViewModel: MyViewModel
    private lateinit var currencies: List<CurrencyResponse>
    private lateinit var adapter:AllCurrenciesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllCurrenciesBinding.inflate(layoutInflater)
        dao=AppDatabase.get.getDatabase().getDao()
        initViewModel()
        loadCurrencies()

        return binding.root
    }

    private fun loadAdapter() {
        val rv = binding.allCurrenciesRv
        adapter = AllCurrenciesAdapter(currencies, object : AllCurrenciesAdapter.OnItemClick {
            override fun onItemClickListener(position: Int) {
                val bundle = Bundle()
                bundle.putInt("position", position)
                findNavController().navigate(R.id.calculator_fragment, bundle)
            }
        })
        rv.layoutManager = LinearLayoutManager(binding.root.context)
        rv.adapter = adapter
    }

    private fun loadCurrencies() {
        myViewModel.getCurrencies().observe(viewLifecycleOwner, Observer {
            currencies = it
            binding.allCurrenciesProgress.visibility=View.GONE
            loadAdapter()

            it.forEach{
                dao.insertCurrency(it)
            }
        })

    }

    private fun initViewModel() {
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val myActionMenuItem = menu.findItem(R.id.search)
        val searchView = myActionMenuItem.actionView as SearchView
        val txtSearch =
            searchView.findViewById<View>(androidx.appcompat.R.id.search_src_text) as EditText
        txtSearch.hint = "Search currency"
        txtSearch.setHintTextColor(Color.LTGRAY)
        txtSearch.setBackgroundResource(android.R.color.transparent)
        txtSearch.setTextColor(Color.BLACK)
        txtSearch.addTextChangedListener {
            filter(it.toString())
        }
        super.onCreateOptionsMenu(menu, inflater)
    }
    private fun filter(query: String) {
        val temp: MutableList<CurrencyResponse> = ArrayList()
        for (d in currencies) {
            if (d.code.lowercase().contains(query.lowercase())) {
                temp.add(d)
            }
        }
        adapter.updateList(temp)
    }
}