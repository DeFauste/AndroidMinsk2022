package com.example.weather.remote.data

data class WeatherResponse(
    val cnt: Double,
    val cod: String,
    val list: List<Weather>,
    val message: Double
)