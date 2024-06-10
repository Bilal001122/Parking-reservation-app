package com.example.parkirapp.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "reservation",
    foreignKeys = [ForeignKey(
        entity = Parking::class,
        parentColumns = ["id"],
        childColumns = ["parkingId"],
        onDelete = ForeignKey.CASCADE
    )],
)
data class Reservation(
    @PrimaryKey
    val id: Int,
    val userId : Int,
    val startHour: String,
    val endHour: String,
    val status: String,
    val placeNumber: Int,
    val qrCode : String,
    val totalPrice: Double,
    val date : String,
    val parkingId : Int,
    val updatedAt: String?,
    val createdAt: String?
)