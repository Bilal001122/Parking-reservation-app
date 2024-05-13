package com.example.parkirapp.business_logic.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parkirapp.data.api.models.Reservation
import com.example.parkirapp.data.api.repos.ReservationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReservationVM(
    private val reservationRepository: ReservationRepository
) : ViewModel() {

    val allReservations = mutableListOf<Reservation>()
    val isLoading = mutableStateOf(false)

    fun getAllReservations(authHeader: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isLoading.value = true
                try {
                    val response = reservationRepository.getUserBookings(authHeader)
                    if (response.isSuccessful) {
                        isLoading.value = false
                        allReservations.clear()
                        allReservations.addAll(response.body()!!)
                    }
                } catch (e: Exception) {
                    isLoading.value = false
                    e.printStackTrace()
                }
            }
        }
    }

    fun cancelReservation(authHeader: String, bookingId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isLoading.value = true
                try {
                    val response = reservationRepository.cancelBooking(authHeader, bookingId)
                    if (response.isSuccessful) {
                        isLoading.value = false
                        getAllReservations(authHeader)
                    }
                } catch (e: Exception) {
                    isLoading.value = false
                    e.printStackTrace()
                }
            }
        }
    }

    fun completeReservation(authHeader: String, bookingId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isLoading.value = true
                try {
                    val response = reservationRepository.completeBooking(authHeader, bookingId)
                    if (response.isSuccessful) {
                        isLoading.value = false
                        getAllReservations(authHeader)
                    }
                } catch (e: Exception) {
                    isLoading.value = false
                    e.printStackTrace()
                }
            }
        }
    }

    fun createReservation(
        authHeader: String,
        parkingId: Int,
        date: String,
        startHour: String,
        endHour: String,
        totalPrice: Double
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                isLoading.value = true
                try {
                    val response = reservationRepository.createBooking(
                        authHeader,
                        parkingId,
                        startHour,
                        endHour,
                        date,
                        totalPrice
                    )
                    if (response.isSuccessful) {
                        isLoading.value = false
                        getAllReservations(authHeader)
                    }
                } catch (e: Exception) {
                    isLoading.value = false
                    e.printStackTrace()
                }
            }
        }
    }

    class Factory(
        private val reservationRepository: ReservationRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReservationVM(reservationRepository) as T
        }
    }
}