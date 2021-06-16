package com.weather.utils

import com.weather.model.FactDTO
import com.weather.model.Weather
import com.weather.model.WeatherDTO
import com.weather.model.getDefaultCity

fun convertDtoToModel(weatherDTO: WeatherDTO): List<Weather> {
    val fact: FactDTO = weatherDTO.fact!!
    return listOf(
        Weather(
            getDefaultCity(), fact.temp!!, fact.feels_like!!, fact.condition!!,
            fact.icon
        )
    )
}