package com.example.parkirapp.data.models

import androidx.annotation.DrawableRes

data class Parking(
    val id: Int,
    val name: String,
    val exactLocationDetails: String,
    @DrawableRes val image: Int,
    val capacity: Int,
    val openAt : String,
    val closingAt : String,
    val description : String,
    val pricePerHour : Double,
    val distanceFromLocation: Double
)
