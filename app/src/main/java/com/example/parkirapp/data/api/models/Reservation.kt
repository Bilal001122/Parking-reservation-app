package com.example.parkirapp.data.api.models

import androidx.annotation.DrawableRes

enum class ReservationStatus {
    ONGOING,
    COMPLETED,
    CANCELED
}
class Reservation (
    val id: Int,
    val startHour: String,
    val endHour: String,
    val status: ReservationStatus,
    val placeNumber: Int,
    val totalPrice: Double,
    val parkingName: String,
    @DrawableRes val parkingImage: Int,
    val parkingAddress: String,
)