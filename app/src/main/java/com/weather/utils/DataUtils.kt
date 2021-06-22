package com.weather.utils

import com.weather.model.FactDTO
import com.weather.model.Weather
import com.weather.model.WeatherDTO
import com.weather.model.getDefaultCity
import com.weather.room.HistoryEntity
import com.weather.weather.City

fun convertDtoToModel(weatherDTO: WeatherDTO): List<Weather> {
    val fact: FactDTO = weatherDTO.fact!!
    return listOf(
        Weather(
            getDefaultCity(), fact.temp!!, fact.feels_like!!, fact.condition!!,
            fact.icon
        )
    )
}

fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> {
    return entityList.map {
        Weather(City(it.city, 0.0, 0.0), it.temperature, 0, it.condition)
    }
}

fun convertWeatherToEntity(weather: Weather): HistoryEntity {
    return HistoryEntity(0, weather.city.cityName, weather.temperature, weather.condition)
}