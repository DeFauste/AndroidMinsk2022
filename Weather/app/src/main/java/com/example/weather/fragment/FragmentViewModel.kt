package com.example.weather.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.remote.RetrofitInstance
import com.example.weather.remote.data.Weather
import com.example.weather.remote.data.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class FragmentViewModel : ViewModel() {
    private val weatherListFlow = flow<WeatherResponse> {
        val response = try {
            RetrofitInstance.api.getWeather("London", "ru", "metric")
        } catch (e: IOException) {
            println("onCreate: not internet")
            return@flow
        } catch (e: HttpException) {
            println("HttpException")
            return@flow
        }
        this.emit(response.body()!!)
    }
    private var weatherList = MutableLiveData<List<Weather>>()
    fun getWeather() = weatherList

    fun updateWeather() {
        viewModelScope.launch {
            weatherListFlow.collect() {
                weatherList.value = it.list
            }
        }
    }
}