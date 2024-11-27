package com.example.todaysweather.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todaysweather.ui.DailyForecast
import com.example.todaysweather.ui.components.WeatherIcon

@Composable
fun WeeklyForecastSection(forecast: List<DailyForecast>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "7-Day Forecast",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = Color.White
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(forecast) { daily ->
                DayForecastCard(daily)
            }

            items(7 - forecast.size) { index ->
                DayForecastCard(
                    DailyForecast(
                        day = getNextDay(forecast.lastOrNull()?.day, index + 1),
                        temperature = "N/A",
                        maxTemp = "N/A",
                        minTemp = "N/A",
                        iconCode = "//cdn.weatherapi.com/weather/64x64/day/116.png",
                        rainChance = "N/A",
                        humidity = "N/A",
                        windSpeed = "N/A"
                    )
                )
            }
        }
    }
}

private fun getNextDay(lastDay: String?, offset: Int): String {
    val daysOfWeek = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    val lastDayIndex = lastDay?.let { daysOfWeek.indexOf(it) } ?: -1
    return daysOfWeek[(lastDayIndex + offset) % 7]
}

@Composable
private fun DayForecastCard(forecast: DailyForecast) {
    Card(
        modifier = Modifier.width(110.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x30FFFFFF)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = forecast.day,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            WeatherIcon(
                iconCode = forecast.iconCode,
                size = 48
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = forecast.temperature,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}