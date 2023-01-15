package com.example.weather.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.weather.R
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.remote.data.Clouds
import com.example.weather.remote.data.Main
import com.example.weather.remote.data.Weather

class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    private val fragmentViewModel: FragmentViewModel by activityViewModels()

    private val adapter: WeatherAdapter = WeatherAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //hide action bar
        (activity as AppCompatActivity?)?.supportActionBar?.hide()

        binding.btnAddCity.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_weatherFragment_to_cityFragment)
        }
        fragmentViewModel.updateWeather()

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rcvWeatherWeek.adapter = adapter
        lifecycleScope.launchWhenCreated {
            fragmentViewModel.getWeather().observe(activity as LifecycleOwner) {
                adapter.weathers = it
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}