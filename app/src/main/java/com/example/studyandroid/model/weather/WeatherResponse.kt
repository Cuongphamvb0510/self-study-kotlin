package com.example.studyandroid.model.weather

data class WeatherResponse(
    val name: String,
    val main: MainInfo,
    val weather: List<WeatherItem>
)

data class MainInfo(
    val temp: Double,
    val humidity: Int
)

data class WeatherItem(
    val description: String,
    val icon: String
)