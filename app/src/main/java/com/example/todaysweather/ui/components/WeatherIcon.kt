package com.example.todaysweather.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun WeatherIcon(
    iconCode: String,
    modifier: Modifier = Modifier,
    size: Int = 64
) {
    // WeatherAPI.com provides icons via HTTPS URLs
    val iconUrl = "https:$iconCode"
    
    AsyncImage(
        model = iconUrl,
        contentDescription = "Weather condition icon",
        modifier = modifier.size(size.dp),
        contentScale = ContentScale.Fit
    )
} 