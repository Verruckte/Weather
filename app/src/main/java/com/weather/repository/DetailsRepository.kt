package com.weather.repository

import com.weather.model.WeatherDTO
import retrofit2.Callback

interface DetailsRepository {
    fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callback: Callback<WeatherDTO>
    )
}