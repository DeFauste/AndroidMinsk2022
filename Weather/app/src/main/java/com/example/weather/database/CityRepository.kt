package com.example.weather.database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class CityRepository(private val cityDao: CityDao) {
    val readAllData: Flow<List<City>> = cityDao.readAllCity()
    val readCity: Flow<List<City>> = cityDao.getCurrentCity()
    suspend fun addCity(city: City) {
        cityDao.addCity(city)
    }
    suspend fun updateCheck(cityName:String) {
        cityDao.updateChecked(cityName)
    }



}