package com.example.weather.remote.data

data class Weather(
    val clouds: Clouds,
    val dt: Double,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val sys: Sys,
    val visibility: Double,
    val weather: List<WeatherX>,
    val wind: Wind
)