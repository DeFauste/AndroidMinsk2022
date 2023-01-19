package com.example.weather.fragment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.database.City
import com.example.weather.database.CityDatabase
import com.example.weather.database.CityRepository
import com.example.weather.remote.DataDistribution
import com.example.weather.remote.RetrofitInstance
import com.example.weather.remote.data.Weather
import com.example.weather.remote.data.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class FragmentViewModel : ViewModel() {
    private lateinit var readAllData: LiveData<List<City>>
    private lateinit var repository: CityRepository

    fun initDatabase(context: Context) {
        val cityDao = CityDatabase.getDatabase(context).cityDao()
        repository = CityRepository((cityDao))
        readAllData = repository.readAllData
    }


    fun addCity(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCity(city)
            repository.updateCheck(city.cityName)
        }
    }
    fun readAllData(): LiveData<List<City>> {
        return readAllData
    }

    private val weatherListFlow = flow<WeatherResponse> {
        val response = try {
            RetrofitInstance.api.getWeather("Stambul", "eng", "metric")
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
                weatherList.value = DataDistribution().getWeekWeather(it.list)
            }
        }
    }
}