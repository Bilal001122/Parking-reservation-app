package com.example.parkirapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parking")
data class Parking (
    @PrimaryKey
    val id: Int,
    val allocatedPlaces: Int,
    val maxCapacity: Int,
    val name: String,
    val exactLocationDetails: String,
    val longitude: Double,
    val latitude: Double,
    val pricePerHour: Double,
    val description: String,
    val openAt: String,
    val closingAt: String,
    val image: String,
    val updatedAt: String?,
    val createdAt: String?
)
