package com.example.parkirapp.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.parkirapp.data.database.entities.Reservation

@Dao
interface ReservationDao {

    @Query("SELECT * FROM reservation")
    fun getAllReservations(): List<Reservation>
    @Query("SELECT * FROM reservation WHERE id = :id")
    fun getReservationById(id: Int): Reservation
    @Insert
    fun insertReservation(reservation: Reservation)
    @Delete
    fun deleteReservation(reservation: Reservation)
    @Update
    fun updateReservation(reservation: Reservation)

}