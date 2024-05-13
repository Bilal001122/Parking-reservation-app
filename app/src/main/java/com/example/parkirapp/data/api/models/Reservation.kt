package com.example.parkirapp.data.api.models

import com.google.gson.annotations.SerializedName

data class Reservation (
    val id: Int,
    @SerializedName("UserId")
    val userId : Int,
    val startHour: String,
    val endHour: String,
    val status: String,
    val placeNumber: Int,
    @SerializedName("QrCode")
    val qrCode : String,
    val totalPrice: Double,
    val date : String,
    @SerializedName("ParkingId")
    val parkingId : Int,
    @SerializedName("Parking")
    val parking: Parking,
    @Transient val updatedAt: String?,
    @Transient val createdAt: String?
)
