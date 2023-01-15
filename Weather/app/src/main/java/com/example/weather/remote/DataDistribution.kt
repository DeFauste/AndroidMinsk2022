package com.example.weather.remote

import com.example.weather.remote.data.Weather

class DataDistribution {

    fun getWeekWeather(listWeather: List<Weather>): List<Weather> {
        val fiveDayWeather = arrayListOf<Weather>()
        listWeather.forEach { weather ->
            if (fiveDayWeather.size > 0 && fiveDayWeather.size != 5
                && (fiveDayWeather[fiveDayWeather.lastIndex].dt_txt.split(" ")[0] != weather.dt_txt.split(
                    " ")[0])
            ) {
                fiveDayWeather.add(weather)
            } else if (fiveDayWeather.size == 0) {
                fiveDayWeather.add(weather)
            }
        }
        return fiveDayWeather
    }
}