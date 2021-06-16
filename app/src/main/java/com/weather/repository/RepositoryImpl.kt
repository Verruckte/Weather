package com.weather.repository

import com.weather.model.Weather
import com.weather.model.getRussianCities
import com.weather.model.getWorldCities

class RepositoryImpl : Repository {
        override fun getWeatherFromServer() = Weather()

        override fun getWeatherFromLocalStorageRus() =
            getRussianCities()

        override fun getWeatherFromLocalStorageWorld() =
            getWorldCities()
    }