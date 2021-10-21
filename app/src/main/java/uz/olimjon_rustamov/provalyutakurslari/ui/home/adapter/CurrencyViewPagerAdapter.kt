package uz.olimjon_rustamov.provalyutakurslari.ui.home.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.model.CurrencyResponse
import uz.olimjon_rustamov.provalyutakurslari.ui.home.ViewPagerFragment

class CurrencyViewPagerAdapter(var data: ArrayList<CurrencyResponse>, fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(
        fragmentManager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
    override fun getCount(): Int = data.size

    override fun getItem(position: Int): Fragment {
        return ViewPagerFragment.newInstance(data[position])
    }

}