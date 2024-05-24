package com.example.parkirapp.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.parkirapp.data.database.entities.Parking

@Dao
interface ParkingDao {
    @Query("SELECT * FROM parking")
    fun getAllParkings(): List<Parking>
    @Query("SELECT * FROM parking WHERE id = :id")
    fun getParkingById(id: Int): Parking
    @Insert
    fun insertParking(parking: Parking)
    @Delete
    fun deleteParking(parking: Parking)
    @Update
    fun updateParking(parking: Parking)
    @Insert
    fun insertParkings(parkings: List<Parking>)
}