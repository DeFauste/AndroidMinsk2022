package com.example.weather.fragment.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.databinding.FragmentWeatherBinding
import com.example.weather.fragment.FragmentViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first


class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    private val fragmentViewModel: FragmentViewModel by activityViewModels()

    private val adapter: WeatherAdapter = WeatherAdapter()
    private lateinit var jobWeather: Job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        /*hide action bar*/
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        fragmentViewModel.initDatabase(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLengthCity(view)
        binding.rcvWeatherWeek.adapter = adapter

        binding.btnAddCity.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_weatherFragment_to_cityFragment)
        }
        updateCurrentWeather()
    }

    private fun setLengthCity(view: View) {
        lifecycleScope.launchWhenCreated {
            fragmentViewModel.readAllCity().collect() {
                if (it.isEmpty()) Navigation.findNavController(view)
                    .navigate(R.id.action_weatherFragment_to_cityFragment)

            }
        }
    }

    private fun updateCurrentWeather() {
        fragmentViewModel.updateWeather()
        jobWeather = lifecycleScope.launchWhenCreated {
            fragmentViewModel.wSharedFlow.collect() { listWeather ->
                if (listWeather.isNotEmpty()) {
                    adapter.weathers = listWeather.subList(1, 5)
                    val weather = listWeather[0]
                    with(binding) {
                        txtCurCity.text = fragmentViewModel.getCurrentCity().first()[0].cityName
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        jobWeather.cancel()
        adapter.weathers = emptyList()
    }
}