package com.example.weather.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_table")
data class City(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val cityName: String,
    val check: Boolean
)
