package uz.olimjon_rustamov.provalyutakurslari.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import uz.olimjon_rustamov.provalyutakurslari.databinding.FragmentHomeBinding
import uz.olimjon_rustamov.provalyutakurslari.retrofit.model.CurrencyResponse
import uz.olimjon_rustamov.provalyutakurslari.viewmodel.MyViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)


        return binding.root
    }


}