package com.example.parkirapp.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "reservation",
    foreignKeys = [ForeignKey(
        entity = Parking::class,
        parentColumns = ["id"],
        childColumns = ["parkingId"]
    )],
)
data class Reservation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val price: Double,
    val parkingId: Int,
)