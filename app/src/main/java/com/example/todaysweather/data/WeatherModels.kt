package com.example.todaysweather.data

data class WeatherResponse(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val localtime: String
)

data class Current(
    val temp_c: Double,
    val condition: Condition,
    val humidity: Int,
    val wind_kph: Double,
    val pressure_mb: Double,
    val feelslike_c: Double,
    val uv: Double,
    val cloud: Int,
    val vis_km: Double,
    val wind_dir: String,
    val precip_mm: Double,
    val last_updated: String
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)

data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val day: Day,
    val astro: Astro
)

data class Day(
    val avgtemp_c: Double,
    val maxtemp_c: Double,
    val mintemp_c: Double,
    val condition: Condition,
    val daily_chance_of_rain: Int,
    val totalprecip_mm: Double,
    val avghumidity: Double,
    val maxwind_kph: Double
)

data class Astro(
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String,
    val moon_phase: String
) 