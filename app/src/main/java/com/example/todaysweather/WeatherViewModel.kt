package com.example.todaysweather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todaysweather.data.WeatherRepository
import com.example.todaysweather.data.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()
    private val _weatherState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val weatherState: StateFlow<WeatherUiState> = _weatherState.asStateFlow()

    private var currentLat: Double? = null
    private var currentLon: Double? = null

    fun fetchWeather(lat: Double, lon: Double) {
        currentLat = lat
        currentLon = lon
        loadWeather()
    }

    fun refresh() {
        currentLat?.let { lat ->
            currentLon?.let { lon ->
                loadWeather()
            }
        }
    }

    private fun loadWeather() {
        viewModelScope.launch {
            try {
                _weatherState.value = WeatherUiState.Loading
                val response = repository.getWeather(currentLat!!, currentLon!!)
                _weatherState.value = WeatherUiState.Success(response.toUiModel())
            } catch (e: Exception) {
                _weatherState.value = WeatherUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    private fun WeatherResponse.toUiModel(): WeatherData {
        return WeatherData(
            temperature = "${current.temp_c.toInt()}°C",
            condition = current.condition.text,
            humidity = "${current.humidity}%",
            windSpeed = "${current.wind_kph.toInt()} km/h",
            pressure = "${current.pressure_mb.toInt()} mb",
            location = location.name,
            iconCode = current.condition.icon,
            feelsLike = "Feels like ${current.feelslike_c.toInt()}°C",
            uvIndex = "UV ${current.uv.toInt()}",
            visibility = "${current.vis_km} km",
            precipitation = "${current.precip_mm} mm",
            cloudCover = "${current.cloud}%",
            windDirection = current.wind_dir,
            lastUpdated = "Updated: ${current.last_updated}",
            sunrise = forecast.forecastday.firstOrNull()?.astro?.sunrise ?: "",
            sunset = forecast.forecastday.firstOrNull()?.astro?.sunset ?: "",
            moonPhase = forecast.forecastday.firstOrNull()?.astro?.moon_phase ?: "",
            dailyForecast = forecast.forecastday.map { day ->
                DailyForecast(
                    day = LocalDate.parse(day.date).format(DateTimeFormatter.ofPattern("EEE")),
                    temperature = "${day.day.avgtemp_c.toInt()}°C",
                    maxTemp = "${day.day.maxtemp_c.toInt()}°C",
                    minTemp = "${day.day.mintemp_c.toInt()}°C",
                    iconCode = day.day.condition.icon,
                    rainChance = "${day.day.daily_chance_of_rain}%",
                    humidity = "${day.day.avghumidity.toInt()}%",
                    windSpeed = "${day.day.maxwind_kph.toInt()} km/h"
                )
            }
        )
    }
}

sealed class WeatherUiState {
    data object Loading : WeatherUiState()
    data class Success(val data: WeatherData) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}

data class WeatherData(
    val temperature: String,
    val condition: String,
    val humidity: String,
    val windSpeed: String,
    val pressure: String,
    val location: String,
    val iconCode: String,
    val feelsLike: String,
    val uvIndex: String,
    val visibility: String,
    val precipitation: String,
    val cloudCover: String,
    val windDirection: String,
    val lastUpdated: String,
    val sunrise: String,
    val sunset: String,
    val moonPhase: String,
    val dailyForecast: List<DailyForecast>
)

data class DailyForecast(
    val day: String,
    val temperature: String,
    val maxTemp: String,
    val minTemp: String,
    val iconCode: String,
    val rainChance: String,
    val humidity: String,
    val windSpeed: String
)