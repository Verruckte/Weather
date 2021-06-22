package com.weather.repository

import com.weather.model.Weather
import com.weather.room.HistoryDao
import com.weather.utils.convertHistoryEntityToWeather
import com.weather.utils.convertWeatherToEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDao) : LocalRepository {
    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(localDataSource.all())
    }

    override fun saveEntity(weather: Weather) {
        localDataSource.insert(convertWeatherToEntity(weather))
    }
}