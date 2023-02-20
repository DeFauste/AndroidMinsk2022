package com.example.weather.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCity(city: City)

    @Query("SELECT * FROM city_table ORDER BY id ASC")
    fun readAllCity(): Flow<List<City>>

    @Query("UPDATE city_table SET checkCity=0  WHERE cityName!=:cityName")
    suspend fun updateChecked(cityName:String)

    @Query("UPDATE city_table SET checkCity=1 WHERE cityName=:cityName")
    suspend fun checkCity(cityName: String)

    @Query("SELECT * FROM city_table WHERE checkCity IS :checkN")
    fun getCurrentCity(checkN:Int = 1):Flow<List<City>>

}