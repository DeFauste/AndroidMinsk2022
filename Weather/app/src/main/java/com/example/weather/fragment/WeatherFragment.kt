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
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.remote.DataDistribution
import com.example.weather.remote.data.Clouds
import com.example.weather.remote.data.Main
import com.example.weather.remote.data.Weather
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

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
        update()
        updateCurrentWeather()
        initRecyclerView()
    }

    private fun update() = fragmentViewModel.updateWeather()
    private fun updateCurrentWeather() {
        lifecycleScope.launchWhenCreated {
            fragmentViewModel.getWeather().observe(activity as LifecycleOwner) {
                val weather = it[0]
                with(binding) {
                    txtCurDate.text = weather.dt_txt.split(" ")[0]
                    txtTemperature.text = weather.main.temp.toInt().toString()
                    txtWeatherSum.text = weather.weather[0].main
                    val idImg = weather.weather[0].icon
                    Glide
                        .with(txtWeatherSum)
                        .load("https://openweathermap.org/img/wn/$idImg@2x.png")
                        .centerCrop()
                        .placeholder(R.drawable.progress_bar)
                        .into(imgIcWeather)
                }
            }
        }
    }
    private fun initRecyclerView() {
        binding.rcvWeatherWeek.adapter = adapter
        lifecycleScope.launchWhenCreated {
            fragmentViewModel.getWeather().observe(activity as LifecycleOwner) {
                adapter.weathers = it.subList(1, 5)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}