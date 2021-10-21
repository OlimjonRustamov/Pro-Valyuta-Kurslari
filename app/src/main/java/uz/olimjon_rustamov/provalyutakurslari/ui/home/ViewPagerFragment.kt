package uz.olimjon_rustamov.provalyutakurslari.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.olimjon_rustamov.provalyutakurslari.databinding.FragmentViewPagerBinding
import uz.olimjon_rustamov.provalyutakurslari.retrofit.room.model.CurrencyResponse


class ViewPagerFragment : Fragment() {
    private val ARG_PARAM1 = "currency"

    private var currency: CurrencyResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currency = it.getSerializable(ARG_PARAM1) as CurrencyResponse
        }
    }

    lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerBinding.inflate(layoutInflater)

        binding.currency=currency

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(currency: CurrencyResponse) =
            ViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, currency)
                }
            }
    }
}