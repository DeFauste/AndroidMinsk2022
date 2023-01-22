package com.example.weather.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCity(city: City)

    @Query("SELECT * FROM city_table ORDER BY id ASC")
    fun readAllCity(): LiveData<List<City>>

    @Query("UPDATE city_table SET checkCity=0  WHERE cityName!=:cityName")
    suspend fun updateChecked(cityName:String)

    @Query("SELECT * FROM city_table WHERE checkCity IS :checkN")
    fun getCurrentCity(checkN:Int = 1):LiveData<List<City>>

}