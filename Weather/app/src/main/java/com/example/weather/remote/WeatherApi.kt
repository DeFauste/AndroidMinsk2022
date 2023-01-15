package com.example.weather.remote

import com.example.weather.remote.data.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    //    @Query("q") city: String
    @GET("forecast?appid=ccbdca74b7d122f471fcd15c15998bd9")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("lang") lang: String,
        @Query("units") units: String,
        @Query("cnt") cnt:String = "40"
    ): Response<WeatherResponse>
}