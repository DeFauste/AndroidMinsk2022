package com.example.weather.fragment.city

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.databinding.FragmentCityBinding
import com.example.weather.fragment.FragmentViewModel



class CityFragment : Fragment() {

    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding!!

    private val fragmentViewModel: FragmentViewModel by activityViewModels()

    private val adapter: CityAdapter = CityAdapter(object : onClickListenerCity {
        override fun onClick(cityName: String) {
            fragmentViewModel.checkCity(cityName)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCityBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)?.supportActionBar?.show()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddCity.setOnClickListener {
            CustomInputDialogFragment().show(
                childFragmentManager, CustomInputDialogFragment.TAG)
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rcvCityList.adapter = adapter
        binding.rcvCityList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        lifecycleScope.launchWhenCreated {
            fragmentViewModel.readAllData().collect() {
                adapter.cites = it
                println(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}