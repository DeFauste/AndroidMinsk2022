package com.example.weather.database

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
    suspend fun checkCity(cityName: String) {
        cityDao.checkCity(cityName)
        cityDao.updateChecked(cityName)
    }

    fun getLength() = cityDao.getLength()

}