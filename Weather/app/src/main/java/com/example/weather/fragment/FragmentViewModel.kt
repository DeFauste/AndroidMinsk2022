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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.joinAll
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
        viewModelScope.launch {
            readCity.collect() { city ->
                val nameCity = city[0].cityName
                try {
                    val response =
                        RetrofitInstance.api.getWeather(nameCity, "eng", "metric").body()?.list
                    weatherListS = flow<List<Weather>> {
                        emit(DataDistribution().getWeekWeather(response?: arrayListOf()))
                    }
                } catch (e: IOException) {
                    println("onCreate: not internet")
                } catch (e: HttpException) {
                    println("HttpException")
                }
            }
        }
    }
    fun readCity(): Flow<List<Weather>> {
        viewModelScope.launch {
            readCity = repository.readCity
        }
        return weatherListS
    }

    private val weatherListFlow = flow<List<Weather>> {
        var name: String = "London"
        val response = try {
            RetrofitInstance.api.getWeather(name, "eng", "metric")
        } catch (e: IOException) {
            println("onCreate: not internet")
            return@flow
        } catch (e: HttpException) {
            println("HttpException")
            return@flow
        }
        this.emit(DataDistribution().getWeekWeather(response.body()!!.list))
    }
    private var weatherList = MutableLiveData<List<Weather>>()
    fun getWeather() = weatherListFlow
}