package com.weather.model

import com.weather.weather.City

data class Weather(
        val city: City = getDefaultCity(),
        val temperature: Int = 0,
        val feelsLike: Int = 0
)

fun getDefaultCity() = City(cityName = "Saransk", lat = 54.1838, lon = 45.1749)