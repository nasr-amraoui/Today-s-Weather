package com.example.todaysweather.data

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/forecast.json")
    suspend fun getWeather(
        @Query("q") location: String,
        @Query("days") days: Int = 7,
        @Query("key") apiKey: String = API_KEY,
        @Query("aqi") aqi: String = "no"
    ): WeatherResponse

    companion object {
        private const val API_KEY = "d21e8ac58b0347dc91c175831242009"
    }
} 