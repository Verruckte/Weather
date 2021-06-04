package com.weather.weather

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class City(
        val cityName: String,
        val lat: Double,
        val lon: Double
) : Parcelable