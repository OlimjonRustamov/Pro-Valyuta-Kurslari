package uz.olimjon_rustamov.provalyutakurslari.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import uz.olimjon_rustamov.provalyutakurslari.R
import uz.olimjon_rustamov.provalyutakurslari.databinding.FragmentHomeBinding
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.dao.CurrencyDao
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.database.AppDatabase
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.model.CurrencyResponse
import uz.olimjon_rustamov.provalyutakurslari.ui.home.adapter.CurrencyRvAdapter
import uz.olimjon_rustamov.provalyutakurslari.ui.home.adapter.CurrencyViewPagerAdapter
import uz.olimjon_rustamov.provalyutakurslari.viewmodel.MyViewModel

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao=AppDatabase.get.getDatabase().getDao()
    }

    private lateinit var myViewModel: MyViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var dao: CurrencyDao
    private lateinit var viewPagerAdapter: CurrencyViewPagerAdapter
    private lateinit var currencyRvAdapter: CurrencyRvAdapter
    private lateinit var titleList:ArrayList<String>
    private lateinit var currencies:ArrayList<CurrencyResponse>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        loadData()

        return binding.root
    }
    private fun loadData() {
        titleList = ArrayList()
        currencies= ArrayList()
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.getCurrencies().observe(viewLifecycleOwner, Observer {
            currencies = it as ArrayList<CurrencyResponse>

            binding.progress.visibility = View.INVISIBLE
            binding.text.visibility = View.VISIBLE

            it.forEach {
                dao.insertCurrency(it)
                titleList.add(it.code)

            }
            viewPagerAdapter = CurrencyViewPagerAdapter(it as ArrayList, childFragmentManager)
            binding.vpCard.adapter = viewPagerAdapter
            binding.tabLayout.setupWithViewPager(binding.vpCard)
            binding.tabLayout2.setupWithViewPager(binding.vpCard)
            setTabs()
            rvAdapter(it[0])

            binding.vpCard.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                }

                override fun onPageSelected(position: Int) {
                    rvAdapter(it[position])
                }

                override fun onPageScrollStateChanged(state: Int) {}
            })
        })
    }
    private fun rvAdapter(currency: CurrencyResponse) {
//        val data = getDao.getCurrencyByDateRv(
//            currency.date!!,
//            currency.code!!
//        ).reversed()
//        currencyRvAdapter.setAdapter(data)
//        currencyRvAdapter.notifyDataSetChanged()
//        binding.rv.adapter = currencyRvAdapter
    }

    private fun setTabs() {
        for (i in 0 until binding.tabLayout.tabCount) {
            val tabBind =
                LayoutInflater.from(binding.root.context).inflate(R.layout.tab_item, null, false)
            val tab = binding.tabLayout.getTabAt(i)
            tab?.customView = tabBind

            val tabTextView=tabBind.findViewById<TextView>(R.id.title)
            tabTextView.text = titleList[i]

            if (i == 0) {
                tabTextView.setBackgroundResource(R.drawable.tab_back_selected)
                tabTextView.setTextColor(resources.getColor(R.color.white))
            } else {
                tabTextView.setBackgroundResource(R.drawable.tab_back_unselected)
                tabTextView.setTextColor(resources.getColor(R.color.tab_color))
            }
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val title = tab?.customView?.findViewById<TextView>(R.id.title)
                title?.setBackgroundResource(R.drawable.tab_back_selected)
                title?.setTextColor(resources.getColor(R.color.white))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val title = tab?.customView?.findViewById<TextView>(R.id.title)
                title?.setBackgroundResource(R.drawable.tab_back_unselected)
                title?.setTextColor(resources.getColor(R.color.tab_color))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }



}