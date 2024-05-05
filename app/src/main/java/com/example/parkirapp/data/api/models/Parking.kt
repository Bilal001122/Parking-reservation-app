package com.example.parkirapp.data.api.models

import com.google.gson.annotations.SerializedName

data class Parking(
    @SerializedName("id")
    val id: Int,
    @SerializedName("allocatedPlaces")
    val allocatedPlaces: Int,
    @SerializedName("maxCapacity")
    val maxCapacity: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val exactLocationDetails: String,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("pricePerHour")
    val pricePerHour: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("opensAt")
    val openAt: String,
    @SerializedName("closesAt")
    val closingAt: String,
    @SerializedName("image")
    val image: String,
    @Transient val updatedAt: String?,
    @Transient val createdAt: String?
)
