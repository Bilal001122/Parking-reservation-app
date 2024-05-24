package com.example.parkirapp.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.parkirapp.data.database.entities.Reservation

data class ReservationWithParking(
    val reservationId: Int,
    val userId: Int,
    val startHour: String,
    val endHour: String,
    val status: String,
    val placeNumber: Int,
    val qrCode: String,
    val totalPrice: Double,
    val date: String,
    val parkingId: Int,
    val reservationUpdatedAt: String?,
    val reservationCreatedAt: String?,
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
    val parkingUpdatedAt: String?,
    val parkingCreatedAt: String?
)

@Dao
interface ReservationDao {

    @Query("""
        SELECT 
            r.id AS reservationId,
            r.userId,
            r.startHour,
            r.endHour,
            r.status,
            r.placeNumber,
            r.qrCode,
            r.totalPrice,
            r.date,
            r.parkingId,
            r.updatedAt AS reservationUpdatedAt,
            r.createdAt AS reservationCreatedAt,
            p.allocatedPlaces,
            p.maxCapacity,
            p.name,
            p.exactLocationDetails,
            p.longitude,
            p.latitude,
            p.pricePerHour,
            p.description,
            p.openAt,
            p.closingAt,
            p.image,
            p.updatedAt AS parkingUpdatedAt,
            p.createdAt AS parkingCreatedAt
        FROM reservation r
        INNER JOIN parking p ON r.parkingId = p.id
    """)
    fun getAllReservationsWithParking(): List<ReservationWithParking>
    @Query("SELECT * FROM reservation WHERE id = :id")
    fun getReservationById(id: Int): Reservation

    @Query("SELECT COUNT(*) FROM reservation")
    fun getReservationCount():Int
    @Insert
    fun insertReservation(reservation: Reservation)
    @Delete
    fun deleteReservation(reservation: Reservation)
    @Query("UPDATE reservation SET status = :newStatus WHERE id = :reservationId")
    fun updateReservation(reservationId: Int, newStatus: String)
    @Insert
    fun insertReservations(reservations: List<Reservation>)
}