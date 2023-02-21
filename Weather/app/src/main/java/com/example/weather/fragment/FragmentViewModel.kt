package com.example.weather.fragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.database.City
import com.example.weather.database.CityDatabase
import com.example.weather.database.CityRepository
import com.example.weather.remote.DataDistribution
import com.example.weather.remote.RetrofitInstance
import com.example.weather.remote.data.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class FragmentViewModel : ViewModel() {
    private lateinit var readAllData: Flow<List<City>>
    private lateinit var repository: CityRepository
    private var readCity: Flow<List<City>> = flow {
        this.emit(repository.readCity.first())
    }

    fun initDatabase(context: Context) {
        val cityDao = CityDatabase.getDatabase(context).cityDao()
        repository = CityRepository((cityDao))
        readAllData = repository.readAllData
        readCity = repository.readCity
    }

    fun checkCity(cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCheck(cityName)
            repository.checkCity(cityName)
        }
    }

    fun addCity(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCity(city)
            repository.updateCheck(city.cityName)
        }
    }

    fun readAllData(): Flow<List<City>> {
        return readAllData
    }

    private var weatherListS: Flow<List<Weather>> = flow {
        viewModelScope.launch(Dispatchers.IO) {
            readCity.collect() { city ->
                if (city.isNotEmpty()) {
                    try {
                        val response =
                            RetrofitInstance.api.getWeather(city.first().cityName, "eng", "metric")
                                .body()?.list
                        weatherListS = flow {
                            emit(DataDistribution().getWeekWeather(response ?: arrayListOf()))
                        }
                    } catch (e: IOException) {
                        println("onCreate: not internet")
                    } catch (e: HttpException) {
                        println("HttpException")
                    }
                }
            }
        }
    }

    fun readCityWeather(): Flow<List<Weather>> {
        viewModelScope.launch {
            readCity = repository.readCity
        }
        return weatherListS
    }
}