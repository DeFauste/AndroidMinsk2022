package com.example.weather.database

import androidx.lifecycle.LiveData

class CityRepository(private val cityDao: CityDao) {
    val readAllData:LiveData<List<City>> = cityDao.readAllCity()

    suspend fun addCity(city: City) {
        cityDao.addCity(city)
    }
    suspend fun updateCheck(cityName:String) {
        cityDao.updateChecked(cityName)
    }

}