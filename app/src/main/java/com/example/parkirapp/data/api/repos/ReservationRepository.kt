package com.example.parkirapp.data.api.repos

import com.example.parkirapp.data.api.BookingRequest
import com.example.parkirapp.data.api.CancelBookingRequest
import com.example.parkirapp.data.api.CompleteBookingRequest
import com.example.parkirapp.data.api.CreateBookingRequest
import com.example.parkirapp.data.api.Endpoints
import com.example.parkirapp.data.api.models.Reservation
import com.example.parkirapp.data.database.daos.ParkingDao
import com.example.parkirapp.data.database.daos.ReservationDao
import com.example.parkirapp.data.database.daos.ReservationWithParking
import retrofit2.Response

class ReservationRepository(
    private val reservationDao: ReservationDao,
    private val parkingDao: ParkingDao
) {
    suspend fun getUserBookings(authHeader: String): Response<List<Reservation>> {
        return Endpoints.createEndpoint().getUserBookings(authHeader)
    }

    suspend fun cancelBooking(authHeader: String, bookingId: Int): Response<Reservation> {
        val cancelBookingRequest: CancelBookingRequest = CancelBookingRequest(bookingId)
        return Endpoints.createEndpoint().cancelBooking(authHeader, cancelBookingRequest)
    }

    suspend fun completeBooking(authHeader: String, bookingId: Int): Response<Reservation> {
        val completeBookingRequest: CompleteBookingRequest = CompleteBookingRequest(bookingId)
        return Endpoints.createEndpoint().completeBooking(authHeader, completeBookingRequest)
    }

    suspend fun createBooking(
        authHeader: String,
        parkingId: Int,
        startHour : String,
        endHour : String,
        date : String,
        totalPrice : Double
    ): Response<Reservation> {
        val createBookingRequest: CreateBookingRequest = CreateBookingRequest(
            parkingId,
            BookingRequest(
                startHour,
                endHour,
                date,
                totalPrice
            ),
        )
        return Endpoints.createEndpoint()
            .createBooking(authHeader, createBookingRequest)
    }

    fun saveReservation(reservation: com.example.parkirapp.data.database.entities.Reservation) {
        reservationDao.insertReservation(reservation)
    }

    fun deleteReservation(reservation: com.example.parkirapp.data.database.entities.Reservation) {
        reservationDao.deleteReservation(reservation)
    }

    fun getReservations(): List<ReservationWithParking> {
        return reservationDao.getAllReservationsWithParking()
    }

    fun getReservationById(id: Int): com.example.parkirapp.data.database.entities.Reservation {
        return reservationDao.getReservationById(id)
    }

    fun countReservations(): Int {
        return reservationDao.getReservationCount()
    }

    fun saveReservations(reservations: List<com.example.parkirapp.data.database.entities.Reservation>) {
        reservationDao.insertReservations(reservations)
    }

    fun updateReservation(reservationId : Int, newStatus : String) {
        reservationDao.updateReservation(reservationId, newStatus)
    }
}