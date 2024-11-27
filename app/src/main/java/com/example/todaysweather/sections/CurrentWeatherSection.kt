package com.example.todaysweather.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todaysweather.ui.WeatherData
import com.example.todaysweather.ui.components.WeatherIcon

@Composable
fun CurrentWeatherSection(data: WeatherData) {
    Column(
        modifier = Modifier
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = data.location,
            fontSize = 46.sp,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        WeatherIcon(
            iconCode = data.iconCode,
            size = 180
        )
        Text(
            text = data.temperature,
            style = MaterialTheme.typography.displayLarge,
            color = Color.White
        )
        Text(
            text = data.condition,
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xCCFFFFFF)
        )
        Text(
            text = data.feelsLike,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xB3FFFFFF)
        )
    }
}