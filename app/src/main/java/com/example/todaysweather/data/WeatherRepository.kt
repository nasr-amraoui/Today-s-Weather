package com.example.todaysweather.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class WeatherRepository {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val weatherApi = retrofit.create(WeatherApi::class.java)

    suspend fun getWeather(lat: Double, lon: Double): WeatherResponse {
        return weatherApi.getWeather("$lat,$lon")
    }
} 