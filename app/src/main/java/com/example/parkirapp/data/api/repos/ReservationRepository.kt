package com.example.parkirapp.data.api.repos

import com.example.parkirapp.data.api.BookingRequest
import com.example.parkirapp.data.api.CancelBookingRequest
import com.example.parkirapp.data.api.CompleteBookingRequest
import com.example.parkirapp.data.api.CreateBookingRequest
import com.example.parkirapp.data.api.Endpoints
import com.example.parkirapp.data.api.models.Reservation
import retrofit2.Response

class ReservationRepository(
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
}